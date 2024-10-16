package swp391.userservice.controller;

import org.springframework.web.multipart.MultipartFile;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.AuthenticationResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.OAuth2AuthRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;

/**
 * Author: Nguyen Tien Thuan
 */
public interface IUserController {

    ApiResponse<UserDTO> getById(Long id);

    ApiResponse<UserDTO> getInfoByToken(String token);

    ApiResponse<?> updateAvatar(Long id, MultipartFile multipartFile);

    ApiResponse<?> register(RegisterRequest registerRequest);

    ApiResponse<?> updateInfo(Long id, UpdateInfoRequest updateInfoRequest);

    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

    ApiResponse<AuthenticationResponse> oauth2Authenticate(OAuth2AuthRequest oAuth2AuthRequest);

    ApiResponse<AuthenticationResponse> refreshToken(String refreshToken);

    ApiResponse<?> updateIsSeller(Long id);

    ApiResponse<?> registerVerificationEmail(String verificationCode);


    ApiResponse<?> changePassword(Long id, String oldPassword, String newPassword);

    ApiResponse<?> resetPassword(String email);

    ApiResponse<?> verifyResetOTP(String verificationCode);

    ApiResponse<?> changePass(String newPass, String email);

}
