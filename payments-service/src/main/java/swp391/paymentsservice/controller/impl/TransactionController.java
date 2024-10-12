package swp391.paymentsservice.controller.impl;

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
    public ApiResponse<List<TransactionResponse>> getTransDepositByUserId(@PathVariable Long id) {

        return transactionService.getTransDepositByUserId(id);
    }
}
