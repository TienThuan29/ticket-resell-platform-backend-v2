package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.request.UpdateStaffRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.dto.response.TransactionResponse;
import swp391.adminservice.dto.response.UserResponse;


import java.util.List;

public interface IAdminService {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

    ApiResponse<StaffDTO> updateStaff(Long staffId, UpdateStaffRequest updateStaffRequest);

    ApiResponse<List<StaffDTO>> getListStaffs();

    ApiResponse<List<TransactionResponse>> getListTransactions();

    ApiResponse<List<UserResponse>> getListUsers();

    ApiResponse<?> enableUserAccount(Long userId);

    ApiResponse<?> disableUserAccount(Long userId);

    ApiResponse<?> disableStaffAccount(Long staffId);

    ApiResponse<?> enableStaffAccount(Long staffId);

    ApiResponse<Long> getSumAmountOfDepositTransaction();

    ApiResponse<Long> getSumAmountOfWithdrawalTransaction();

    ApiResponse<Integer> countBoughtTickets();

    ApiResponse<Integer> countSellingTickets();

}
