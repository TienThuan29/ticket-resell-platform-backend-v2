package swp391.paymentsservice.controller.def;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.paymentsservice.dto.request.WithdrawalRequest;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.PaymentResponse;

import java.text.ParseException;

public interface IPaymentController {

    ApiResponse<?> pay(HttpServletRequest request);

    ApiResponse<?> payCallBack(String responseCode,
                               String transactionStatus,
                               Long amount,
                               String transactionType,
                               String payDate,
                               String txnRef,
                               String transactionNo) throws ParseException;

    ApiResponse<?> withdrawalAmount(WithdrawalRequest withdrawalRequest);
}
