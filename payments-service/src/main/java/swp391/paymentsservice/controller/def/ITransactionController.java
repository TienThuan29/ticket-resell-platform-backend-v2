package swp391.paymentsservice.controller.def;

import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.TransactionResponse;

import java.util.List;

public interface ITransactionController {

    ApiResponse<List<TransactionResponse>> getTransDepositByUserId(Long id);
}
