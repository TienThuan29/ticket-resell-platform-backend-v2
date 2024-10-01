package swp391.userservice.controller;

import org.springframework.web.multipart.MultipartFile;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;

/**
 * Author: Nguyen Tien Thuan
 */
public interface IUserController {

    ApiResponse<UserDTO> getById(Long id);

    ApiResponse<?> updateAvatar(Long id, MultipartFile multipartFile);

    ApiResponse<?> register(RegisterRequest registerRequest);

    ApiResponse<?> updateInfo(Long id, UpdateInfoRequest updateInfoRequest);

    ApiResponse<UserDTO> authenticate(AuthenticationRequest authenticationRequest);

    ApiResponse<?> updateIsSeller(Long id);

    ApiResponse<?> registerVerificcationEmail(String verificationCode);

}
