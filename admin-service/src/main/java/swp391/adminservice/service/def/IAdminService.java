package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.dto.response.TransactionResponse;
import swp391.adminservice.dto.response.UserResponse;


import java.util.List;

public interface IAdminService {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

    ApiResponse<List<StaffDTO>> getListStaffs();

    ApiResponse<List<TransactionResponse>> getListTransactions();

    ApiResponse<List<UserResponse>> getListUsers();

<<<<<<< HEAD
    ApiResponse<?> disableUserAccount(Long userId);

    ApiResponse<?> disableStaffAccount(Long staffId);
=======
    ApiResponse<Long> getSumAmountOfDepositTransaction();

    ApiResponse<Long> getSumAmountOfWithdrawalTransaction();

    ApiResponse<Integer> countBoughtTickets();

    ApiResponse<Integer> countSellingTickets();
>>>>>>> eac81e31b2e9187c5885e0f2b268ce711265d986
}
