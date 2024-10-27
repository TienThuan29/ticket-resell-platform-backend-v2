package swp391.adminservice.controller.def;

import org.springframework.web.bind.annotation.PathVariable;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.request.UpdateStaffRequest;
import swp391.adminservice.dto.response.*;


import java.util.List;

public interface IAdminController {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

    ApiResponse<StaffDTO> updateStaff(Long staffId, UpdateStaffRequest updateStaffRequest);

    ApiResponse<List<StaffDTO>> getListStaffs();

    ApiResponse<List<TransactionResponse>> getListTransactions();

    ApiResponse<List<UserResponse>> getAllUsers();

    ApiResponse<?> enableUserAccount(Long userId);

    ApiResponse<?> disableUserAccount(Long userId);

    ApiResponse<?> enableStaffAccount(Long staffId);

    ApiResponse<?> disableStaffAccount(Long staffId);
    ApiResponse<Long> getSumAmountOfDepositTransaction();

    ApiResponse<Long> getSumAmountOfWithdrawalTransaction();

    ApiResponse<Integer> countBoughtTickets();

    ApiResponse<Integer> countSellingTickets();

}
