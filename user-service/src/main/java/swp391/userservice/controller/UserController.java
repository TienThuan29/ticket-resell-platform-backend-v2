package swp391.userservice.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.AuthenticationResponse;
import swp391.userservice.dto.reponse.NotificationResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.OAuth2AuthRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;
import swp391.userservice.service.IUserService;

import java.util.List;

/**
 * Author: Nguyen Tien Thuan
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
public class UserController implements IUserController {

    private final IUserService userService;

    @Override
    @GetMapping("/get/info/{id}")
    public ApiResponse<UserDTO> getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @Override
    @GetMapping("/get/info")
    public ApiResponse<UserDTO> getInfoByToken(@RequestHeader("Authorization") String token) {
        //log.info("Token: "+token);
        return userService.getInfoByToken(token);
    }

    @Override
    @PostMapping(value = "/profile/update/avatar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<?> updateAvatar(
            @PathVariable("id") Long id,
            @Parameter(description = "Avatar image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestParam("image") MultipartFile file
    ) {
        return userService.updateAvatar(id, file);
    }

    @Override
    @PutMapping("/update/seller/agree/{id}")
    public ApiResponse<?> updateIsSeller(@PathVariable("id") Long id) {
        return userService.updateIsSeller(id);
    }

    @PutMapping("/register/verify-email")
    @Override
    public ApiResponse<?> registerVerificationEmail(@RequestParam String verificationCode) {
        return userService.verifyEmail(verificationCode);
    }

    @Override
    @PostMapping("/change-pass")
    public ApiResponse<?> changePassword(
            @RequestParam("id") Long id,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword
    ) {
        return userService.changePassword(id, oldPassword, newPassword);
    }


    @PostMapping("/reset-password")
    public ApiResponse<?> resetPassword(@RequestParam String email) {
        return userService.resetPassword(email);
    }

    @Override
    @PostMapping("/reset-password/verify-reset-otp")
    public ApiResponse<?> verifyResetOTP(@RequestParam String verificationCode) {
        return userService.verifyResetOTP(verificationCode);
    }

    @Override
    @PutMapping("/reset-password/new-pass")
    public ApiResponse<?> changePass(@RequestParam String newPass, @RequestParam String email) {
        return userService.changePass(newPass, email);
    }

    @Override
    @GetMapping("/get-by-name")
    public ApiResponse<List<UserDTO>> getUsersByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }

    @Override
    @PostMapping("/authenticate")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authenticate(authenticationRequest);
    }

    @Override
    @PostMapping("/oauth2/authenticate")
    public ApiResponse<AuthenticationResponse> oauth2Authenticate(
            @RequestBody OAuth2AuthRequest oAuth2AuthRequest
    ) {
        return userService.oauth2Authenticate(oAuth2AuthRequest.getEmail());
    }

    @Override
    @PostMapping("/refresh-token")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return userService.refreshToken(refreshToken);
    }

    @Override
    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<UserDTO> updateInfo(
            @PathVariable("id") Long id,
            @RequestBody UpdateInfoRequest updateInfoRequest
    ) {
        return userService.update(id, updateInfoRequest);
    }

    @GetMapping("/get/notifications/all/{receiverId}")
    public ApiResponse<List<NotificationResponse>> getAllNotificationOfReceiver(@PathVariable("receiverId") Long receiverId){
        return userService.getAllNotificationOfReceiver(receiverId);
    }

}
