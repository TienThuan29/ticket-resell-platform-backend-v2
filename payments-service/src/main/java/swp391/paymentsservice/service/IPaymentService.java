package swp391.paymentsservice.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import swp391.paymentsservice.dto.response.ApiResponse;

import java.text.ParseException;

@Service
public interface IPaymentService {

    ApiResponse<?> createVnPayPayment(HttpServletRequest request);

    ApiResponse<?> saveTransaction(
            String vnp_TxnRef,
            Long amount,
            String transactionType,
            String payDate
    ) throws ParseException;
}
