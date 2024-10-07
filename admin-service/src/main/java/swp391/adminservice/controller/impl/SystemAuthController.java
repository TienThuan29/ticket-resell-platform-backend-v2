package swp391.adminservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.controller.def.ISystemAuthController;
import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.repository.StaffRepository;
import swp391.adminservice.service.def.ISystemAuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system")
public class SystemAuthController implements ISystemAuthController {

    private final ISystemAuthService systemAuthService;


    @Override
    @GetMapping("/get/info")
    public ApiResponse<StaffDTO> getInfoByToken(@RequestHeader("Authorization") String token) {
        return systemAuthService.getInfoByToken(token);
    }

    @Override
    @PostMapping("/authentication")
    public ApiResponse<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest authRequest
    ) {
        return systemAuthService.authentication(authRequest);
    }

    @Override
    @PostMapping("/refresh-token")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return systemAuthService.refreshToken(refreshToken);
    }

}
