package swp391.userservice.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.userservice.configuration.MessageConfiguration;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.AuthenticationResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;
import swp391.userservice.service.IUserService;
import swp391.userservice.service.UserService;

/**
 * Author: Nguyen Tien Thuan
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
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
    @PostMapping("/profile/update/avatar/{id}")
    public ApiResponse<?> updateAvatar(
            @PathVariable("id") Long id,
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
    public ApiResponse<?> registerVerificcationEmail(@RequestParam String verificationCode) {
        return userService.verifyEmail(verificationCode);
    }

    @Override
    @PostMapping("/authenticate")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authenticate(authenticationRequest);
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

}
