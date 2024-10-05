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
