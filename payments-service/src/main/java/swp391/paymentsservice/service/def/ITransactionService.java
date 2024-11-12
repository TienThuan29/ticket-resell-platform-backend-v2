package swp391.paymentsservice.service.def;

import org.springframework.stereotype.Service;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.TransactionResponse;

import java.util.List;

@Service
public interface ITransactionService {

    ApiResponse<List<TransactionResponse>> getAllTransactionByUser(Long id);

    ApiResponse<List<TransactionResponse>> getTransDepositByUserId(Long id);

    ApiResponse<List<TransactionResponse>> getTransWithdrawalByUserId(Long id);

    ApiResponse<List<TransactionResponse>> getTransSellingByUserId(Long id);

    ApiResponse<List<TransactionResponse>> getTransBuyingByUserId(Long id);

    ApiResponse<?> automationScanReportByButton();
}
