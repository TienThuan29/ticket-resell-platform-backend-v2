package swp391.adminservice.controller.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.*;


import java.util.List;

public interface IAdminController {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

    ApiResponse<List<StaffDTO>> getListStaffs();

    ApiResponse<List<TransactionResponse>> getListTransactions();

    ApiResponse<List<UserResponse>> getAllUsers();
}
