package swp391.adminservice.controller.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.*;


import java.util.List;

public interface IAdminController {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

    ApiResponse<List<StaffDTO>> getListStaffs();

    ApiResponse<List<TransactionResponse>> getListTransactions();

    ApiResponse<List<UserResponse>> getAllUsers();

    ApiResponse<?> disableUserAccount(Long userId);

    ApiResponse<?> disableStaffAccount(Long staffId);
    ApiResponse<Long> getSumAmountOfDepositTransaction();

    ApiResponse<Long> getSumAmountOfWithdrawalTransaction();

    ApiResponse<Integer> countBoughtTickets();

    ApiResponse<Integer> countSellingTickets();

    ApiResponse<Long> getRevenue();
    ApiResponse<List<EventRevenueResponse>> getEventsRevenue();
}
