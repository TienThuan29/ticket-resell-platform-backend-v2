package swp391.adminservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.adminservice.controller.def.IAdminController;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.service.def.IAdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController implements IAdminController {

    private final IAdminService adminService;

    @Override
    @PostMapping("/register-staff")
    public ApiResponse<StaffDTO> registerStaff(@RequestBody RegisterRequest registerRequest) {
        return adminService.registerStaff(registerRequest);
    }
}
