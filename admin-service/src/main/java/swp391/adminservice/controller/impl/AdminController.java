package swp391.adminservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import swp391.adminservice.controller.def.IAdminController;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.dto.response.TransactionResponse;
import swp391.adminservice.dto.response.UserResponse;
import swp391.adminservice.service.def.IAdminService;

import java.util.List;

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

    @Override
    @GetMapping("/get-list-staff")
    public ApiResponse<List<StaffDTO>> getListStaffs() {
        return adminService.getListStaffs();
    }

    @Override
    @GetMapping("/get-list-transaction")
    public ApiResponse<List<TransactionResponse>> getListTransactions() {
        return adminService.getListTransactions();
    }

    @Override
    @GetMapping("/get-list-user")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return adminService.getListUsers();
    }
}
