package swp391.adminservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.configuration.ConstantConfiguration;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.exception.def.NotFoundException;
import swp391.adminservice.mapper.StaffMapper;
import swp391.adminservice.repository.StaffRepository;
import swp391.adminservice.repository.StaffTokenRepository;
import swp391.adminservice.service.def.ISystemAuthService;
import swp391.entity.Staff;
import swp391.entity.StaffToken;
import swp391.entity.fixed.TokenType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemAuthService implements ISystemAuthService {

    private final JwtService jwtService;

    private final StaffRepository staffRepository;

    private final MessageConfiguration messageConfig;

    private final StaffTokenRepository staffTokenRepository;

    private final ConstantConfiguration constant;

    private final StaffMapper staffMapper;


    @Override
    public ApiResponse<StaffDTO> getInfoByToken(String token) {
        token = token.substring(constant.AUTHENTICATION_HEADER_BEARER.length());
        if (token.trim().isEmpty())
            return new ApiResponse<>(HttpStatus.FORBIDDEN, messageConfig.ERROR_INVALID_TOKEN, null);

        String username;
        try {
            username = jwtService.extractUsername(token);
            var staff = staffRepository.findEnableAccount(username).orElseThrow(
                    () -> new NotFoundException(messageConfig.ERROR_USERNAME_NOTFOUND)
            );

            if (!jwtService.isValidToken(token, staff)) throw new Exception();

            return new ApiResponse<>(HttpStatus.OK, "", staffMapper.toDTO(staff));
        }
        catch (Exception ex) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN, messageConfig.ERROR_INVALID_TOKEN, null);
        }
    }

    @Override
    public ApiResponse<AuthenticationResponse> authentication(AuthenticationRequest authRequest) {
        var staff = staffRepository.findEnableAccount(authRequest.getUsername());

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>(
                HttpStatus.FORBIDDEN,
                messageConfig.ERROR_INVALID_USERNAME_PASSWORD, null
        );

        if (staff.isPresent()) {
            var matchedPassword = BCrypt.checkpw(authRequest.getPassword(), staff.get().getPassword());
            if (matchedPassword){
                String jwtToken = jwtService.generateToken(staff.get()); // Access token
                String refreshToken = jwtService.generateRefreshToken(staff.get());  // Refresh token

                revokeAllOldUserToken(staff.get());
                saveToken(staff.get(), jwtToken);
                apiResponse = new ApiResponse<>(
                        HttpStatus.OK, "",
                        AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .refreshToken(refreshToken)
                                .build()
                );
            }
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<AuthenticationResponse> refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty())
            return new ApiResponse<>(HttpStatus.FORBIDDEN, messageConfig.ERROR_INVALID_TOKEN, null);

        final String username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            Staff staff = staffRepository.findEnableAccount(username).get();
            if(jwtService.isValidToken(refreshToken, staff)) {
                String newAccessToken = jwtService.generateToken(staff);
                revokeAllOldUserToken(staff);
                saveToken(staff, newAccessToken);
                return new ApiResponse<>(HttpStatus.OK, "",
                        AuthenticationResponse.builder()
                                .accessToken(newAccessToken)
                                .refreshToken(refreshToken)
                                .build()
                );
            }
        }
        return new ApiResponse<>(HttpStatus.FORBIDDEN, messageConfig.ERROR_INVALID_TOKEN, null);
    }

    private void saveToken(Staff staff, String jwtToken) {
        StaffToken token = StaffToken.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(constant.JWT_EXPIRED_DISABLE)
                .revoked(constant.JWT_REVOKED_DISABLE)
                .staff(staff)
                .build();
        staffTokenRepository.save(token);
    }

    private void revokeAllOldUserToken(Staff staff) {
        List<StaffToken> validTokenList = staffTokenRepository.findAllValidTokenByUser(staff.getId());
        if (!validTokenList.isEmpty()) {
            staffTokenRepository.deleteAll(validTokenList);
        }
    }

}
