package swp391.userservice.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.AuthenticationResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;

/**
 * Author: Nguyen Tien Thuan
 */
public interface IUserService {

    ApiResponse<UserDTO> getById(Long id);

    ApiResponse<UserDTO> getInfoByToken(String token);

    ApiResponse<?> updateAvatar(Long id, MultipartFile file);

    ApiResponse<?> updateIsSeller(Long id);

    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

    ApiResponse<AuthenticationResponse> refreshToken(String refreshToken);

    ApiResponse<?> changePassword(Long id, String oldPassword, String newPassword);

    ApiResponse<?> register(RegisterRequest registerRequest);

    ApiResponse<?> verifyEmail(String verificationOTP);

    ApiResponse<UserDTO> update(Long id, UpdateInfoRequest updateInfoRequest);

}
