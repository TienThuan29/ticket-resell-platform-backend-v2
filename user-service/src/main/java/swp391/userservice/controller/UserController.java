package swp391.userservice.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.userservice.configuration.MessageConfiguration;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;
import swp391.userservice.service.IUserService;
import swp391.userservice.service.UserService;

/**
 * Author: Nguyen Tien Thuan
 */
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

    @Override
    @PostMapping("/authenticate")
    public ApiResponse<UserDTO> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authenticate(authenticationRequest);
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
