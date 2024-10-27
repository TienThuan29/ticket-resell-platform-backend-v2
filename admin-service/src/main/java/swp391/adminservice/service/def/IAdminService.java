package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.*;


import java.util.List;

public interface IAdminService {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

    ApiResponse<List<StaffDTO>> getListStaffs();

    ApiResponse<List<TransactionResponse>> getListTransactions();

    ApiResponse<List<UserResponse>> getListUsers();


    ApiResponse<?> disableUserAccount(Long userId);

    ApiResponse<?> disableStaffAccount(Long staffId);

    ApiResponse<Long> getSumAmountOfDepositTransaction();

    ApiResponse<Long> getSumAmountOfWithdrawalTransaction();

    ApiResponse<Integer> countBoughtTickets();

    ApiResponse<Integer> countSellingTickets();

    ApiResponse<List<EventRevenueResponse>> getEventsRevenue();
}
