package swp391.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.Token;
import swp391.entity.User;
import swp391.entity.VerificationUser;
import swp391.entity.fixed.TokenType;
import swp391.entity.fixed.VerificationType;
import swp391.userservice.configuration.ConstantConfiguration;
import swp391.userservice.configuration.MessageConfiguration;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.AuthenticationResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;
import swp391.userservice.exception.def.NotFoundException;
import swp391.userservice.mapper.UserMapper;
import swp391.userservice.repository.TokenRepository;
import swp391.userservice.repository.UserRepository;
import swp391.userservice.repository.VerificationUserRepository;
import swp391.userservice.utils.ImageUtil;
import swp391.userservice.utils.RandomUtil;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Nguyen Tien Thuan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final VerificationUserRepository verificationRepository;

    private final MessageConfiguration messageConfig;

    private final UserMapper userMapper;

    private final EmailServiceFeign emailService;

    private final RandomUtil randomUtil;

    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final ConstantConfiguration constant;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<UserDTO> getById(Long id) {
        var user = userRepository.findById(id);
        return new ApiResponse<>(HttpStatus.OK, "", userMapper.toUserDTO(user.get()));
    }

    @Override
    public ApiResponse<UserDTO> getInfoByToken(String token) {
        token = token.substring(constant.AUTHENTICATION_HEADER_BEARER.length());
        if (token.trim().isEmpty())
            return new ApiResponse<>(HttpStatus.FORBIDDEN, messageConfig.ERROR_INVALID_TOKEN, null);

        String username;
        try {
            username = jwtService.extractUsername(token);
            var user = userRepository.findEnableAccount(username).orElseThrow(
                    () -> new NotFoundException(messageConfig.ERROR_USERNAME_NOTFOUND)
            );

            if (!jwtService.isValidToken(token, user)) throw new Exception();

            return new ApiResponse<>(HttpStatus.OK, "", userMapper.toUserDTO(user));
        }
        catch (Exception ex) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN, messageConfig.ERROR_INVALID_TOKEN, null);
        }
    }

    @Override
    public ApiResponse<?> updateAvatar(Long id, MultipartFile file) {
        if (file.isEmpty())
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_AVATAR_FAIL, null);

        var user = userRepository.findById(id).get();
        try {
            user.setAvatar(ImageUtil.compressImage(file.getBytes()));
            userRepository.save(user);
        }
        catch (IOException exception) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_AVATAR_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.MESSAGE_UPDATE_AVATAR_SUCCESS, null);
    }

    @Override
    public ApiResponse<?> updateIsSeller(Long id) {
        try {
            userRepository.updateIsSellerById(id);
        }
        catch (Exception exception) {
            log.info("Exception: "+exception.getMessage());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_ISSELLER_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.MESSAGE_UPDATE_ISSELLER_SUCCESS, null);
    }


//    @Override
//    public ApiResponse<UserDTO> authenticate(AuthenticationRequest authRequest) {
//        var user = userRepository.findEnableAccount(authRequest.getUsername());
//
//        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(
//                HttpStatus.FORBIDDEN,
//                messageConfig.ERROR_INVALID_USERNAME_PASSWORD, null
//        );
//
//        if (user.isPresent()) {
//            var matchedPassword = BCrypt.checkpw(authRequest.getPassword(), user.get().getPassword());
//            if (matchedPassword){
//                apiResponse = new ApiResponse<>(HttpStatus.OK, "", userMapper.toUserDTO(user.get()));
//            }
//
//        }
//        return apiResponse;
//    }

    @Override
    public ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest authRequest) {
        var user = userRepository.findEnableAccount(authRequest.getUsername());

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>(
                HttpStatus.FORBIDDEN,
                messageConfig.ERROR_INVALID_USERNAME_PASSWORD, null
        );

        if (user.isPresent()) {
            var matchedPassword = BCrypt.checkpw(authRequest.getPassword(), user.get().getPassword());
            if (matchedPassword){
                String jwtToken = jwtService.generateToken(user.get()); // Access token
                String refreshToken = jwtService.generateRefreshToken(user.get());  // Refresh token

                revokeAllOldUserToken(user.get());
                saveToken(user.get(), jwtToken);
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
            User user = userRepository.findEnableAccount(username).get(); // Get entity
            if(jwtService.isValidToken(refreshToken, user)) {
                String newAccessToken = jwtService.generateToken(user);
                revokeAllOldUserToken(user);
                saveToken(user, newAccessToken);
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

    private void saveToken(User user, String jwtToken) {
        Token token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(constant.JWT_EXPIRED_DISABLE)
                .revoked(constant.JWT_REVOKED_DISABLE)
                .user(user)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllOldUserToken(User user) {
        List<Token> validTokenList = tokenRepository.findAllValidTokenByUser(user.getId());
        if (!validTokenList.isEmpty()) {
            tokenRepository.deleteAll(validTokenList);
        }
    }

    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        var user = userRepository.findByUsername(registerRequest.getUsername());

        if (user.isPresent())
            return new ApiResponse<>(HttpStatus.CONFLICT, messageConfig.ERROR_USERNAME_EXIST, null);

        if(this.isExistEmail(registerRequest.getEmail()))
            return new ApiResponse<>(HttpStatus.CONFLICT, messageConfig.ERROR_EXIST_EMAIL, null);

        User newUser= userRepository.save(userMapper.registerRequestToEntity(registerRequest));

        VerificationUser verificationUser= VerificationUser.builder()
                .userId(newUser.getId())
                .startTime(System.currentTimeMillis())
                .verificationCode(randomUtil.generateRandomCode(6))
                .verificationType(VerificationType.VERIFY_EMAIL).build();
        verificationRepository.save(verificationUser);
        emailService.sendOtpEmail(verificationUser, newUser.getEmail());

        return new ApiResponse<>(HttpStatus.CREATED, messageConfig.ERROR_REGISTER_SUCCESS, null);
    }


    private boolean isExistEmail(String email) {
        var user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public ApiResponse<?> verifyEmail(String verificationOTP) {
        // 300000 milliseconds = 5 minute
        var verifyUserOpt = verificationRepository.findByVerificationCode(verificationOTP);

        if (verifyUserOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, messageConfig.ERROR_OTP_INVALID);
        }

        var verifyUser = verifyUserOpt.get();
        var userOpt = userRepository.findById(verifyUser.getUserId());

        // Http.NOT_FOUND, not OK
        if (userOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, messageConfig.ERROR_NOT_FOUND_USERID, Boolean.FALSE);
        }

        var user = userOpt.get();
        long period = System.currentTimeMillis() - verifyUser.getStartTime();

        if (period < 300000) {
            user.setIsEnable(Boolean.TRUE);
            userRepository.save(user);
            verificationRepository.delete(verifyUser);
            return new ApiResponse<>(HttpStatus.OK, messageConfig.ERROR_REGISTER_SUCCESS, Boolean.TRUE);
        } else { // Nếu OTP hết hạn
            verificationRepository.delete(verifyUser);
            userRepository.delete(user);
            return new ApiResponse<>(HttpStatus.NOT_FOUND, messageConfig.ERROR_OTP_ISEXPIRED, Boolean.FALSE);
        }
    }

    @Override
    public ApiResponse<UserDTO> update(Long id, UpdateInfoRequest updateInfoRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageConfig.ERROR_NOT_FOUND_USERID)
        );
        user.setFirstname(updateInfoRequest.getFirstname());
        user.setLastname(updateInfoRequest.getLastname());
        user.setPhone(
                updateInfoRequest.getPhone().trim().isEmpty() ? null : updateInfoRequest.getPhone()
        );
        user.setEmail(updateInfoRequest.getEmail());
        return new ApiResponse<>(
                HttpStatus.OK,
                messageConfig.MESSAGE_UPDATE_USER_SUCCESS,
                userMapper.toUserDTO(userRepository.save(user))
        );
    }

    @Override
    public ApiResponse<?> resetPassword(String email) {
        if(!isExistEmail(email)){
            throw new NotFoundException(messageConfig.ERROR_INVALID_EMAIL);
        }
        var user= userRepository.findByEmail(email).get();
        VerificationUser verificationUser= VerificationUser.builder()
                .userId(user.getId())
                .startTime(System.currentTimeMillis())
                .verificationCode(randomUtil.generateRandomCode(6))
                .verificationType(VerificationType.RESET_PASSWORD).build();
        verificationRepository.save(verificationUser);
        emailService.sendResetOTP(verificationUser, email);
        return new ApiResponse<>(HttpStatus.OK, "", Boolean.TRUE);
    }

    @Override
    public ApiResponse<?> verifyResetOTP(String verificationOTP) {
        var verifyUser = verificationRepository.findByVerificationCode(verificationOTP)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_OTP_INVALID+" :"+verificationOTP));
        var user = userRepository.findById(verifyUser.getUserId())
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_NOT_FOUND_USERID));

        long period = System.currentTimeMillis() - verifyUser.getStartTime();

        if (period < 300000) {
            user.setIsEnable(Boolean.TRUE);
            userRepository.save(user);
            verificationRepository.delete(verifyUser);
            return new ApiResponse<>(HttpStatus.OK, "", Boolean.TRUE);
        } else { // Nếu OTP hết hạn
            verificationRepository.delete(verifyUser);
            userRepository.delete(user);
            return new ApiResponse<>(HttpStatus.NOT_FOUND, messageConfig.ERROR_OTP_ISEXPIRED, Boolean.FALSE);
        }
    }

    @Override
    public ApiResponse<?> changePass(String newPass, String email) {
        var user= userRepository.findByEmail(email).get();
        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
        return new ApiResponse<>(HttpStatus.OK, "Success");
    }

    @Scheduled(fixedDelay = 300000)// 5 phút reset một lần
    public void resetVerifyCode() {
        long currentTime = System.currentTimeMillis();
        long otpValidityPeriod = 300000; // 300000 milliseconds = 5 minute

        var expiredOtps = verificationRepository.findAll()
                .stream()
                .filter(verificationUser -> verificationUser.getVerificationType() == VerificationType.VERIFY_EMAIL)
                .filter(verificationUser -> currentTime > verificationUser.getStartTime() + otpValidityPeriod)
                .toList();

        for (VerificationUser expiredOtp : expiredOtps) {
            var userOpt = userRepository.findById(expiredOtp.getUserId());

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                if (!user.getIsEnable()) {
                    userRepository.delete(user);
                }
            }
            verificationRepository.delete(expiredOtp);
        }
    }
}
