package swp391.paymentsservice.controller.impl;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.paymentsservice.controller.def.ITransactionController;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.TransactionResponse;
import swp391.paymentsservice.service.def.ITransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController implements ITransactionController {

    private final ITransactionService transactionService;

    @Override
    @GetMapping("/get-trans-deposit/{id}")
    public ApiResponse<List<TransactionResponse>> getTransDepositByUserId(@Parameter(description= "UserId") @PathVariable Long id) {
        return transactionService.getTransDepositByUserId(id);
    }

    @Override
    @GetMapping("/get-trans-withdrawal/{id}")
    public ApiResponse<List<TransactionResponse>> getTransWithdrawalByUserId(@Parameter(description = "UserId") @PathVariable Long id) {
        return transactionService.getTransWithdrawalByUserId(id);
    }

    @Override
    @GetMapping("/get-trans-selling/{id}")
    public ApiResponse<List<TransactionResponse>> getTransSellingByUserId(@Parameter(description = "UserId") @PathVariable Long id) {
        return transactionService.getTransSellingByUserId(id);
    }

    @Override
    @GetMapping("/get-trans-buying/{id}")
    public ApiResponse<List<TransactionResponse>> getTransBuyingByUserId(@Parameter(description = "UserId") @PathVariable Long id) {
        return transactionService.getTransBuyingByUserId(id);
    }

    @Override
    @GetMapping("/get-all-trans/{userid}")
    public ApiResponse<List<TransactionResponse>> getAllTransactionByUser(@PathVariable("userid") Long id) {
        return transactionService.getAllTransactionByUser(id);
    }

}
