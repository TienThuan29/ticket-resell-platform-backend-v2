package swp391.paymentsservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.PaymentResponse;
import swp391.paymentsservice.service.IPaymentService;
import swp391.paymentsservice.service.PaymentService;

import java.text.ParseException;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController implements IPaymentController{

    private final IPaymentService paymentService;

    @GetMapping("/request")
    @Override
    public ApiResponse<?> pay(HttpServletRequest request) {
        return paymentService.createVnPayPayment(request);
    }

    @GetMapping("/vn-pay-callback")
    @Override
    public ApiResponse<?> payCallBack(
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("vnp_TransactionStatus") String transactionStatus,
            @RequestParam("vnp_Amount") Long amount,
            @RequestParam("vnp_OrderInfo") String transactionType,
            @RequestParam("vnp_PayDate") String payDate,
            @RequestParam("vnp_TxnRef") String txnRef
    ) throws ParseException {
        if (responseCode.equals("00") && transactionStatus.equals("00")){
            return paymentService.saveTransaction(
                    txnRef, amount, transactionType, payDate
            );
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed", Boolean.FALSE);
    }
}