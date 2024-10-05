package swp391.adminservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.adminservice.controller.def.ISystemAuthController;
import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;
import swp391.adminservice.service.def.ISystemAuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system")
public class SystemAuthController implements ISystemAuthController {

    private final ISystemAuthService systemAuthService;

    @Override
    @PostMapping("/authentication")
    public ApiResponse<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest authRequest
    ) {
        return systemAuthService.authentication(authRequest);
    }

}
