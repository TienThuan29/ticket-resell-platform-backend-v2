package swp391.paymentsservice.controller.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import swp391.paymentsservice.controller.def.IPaymentController;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.service.def.IPaymentService;

import java.text.ParseException;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController implements IPaymentController {

    private final IPaymentService paymentService;

    @Override
    @GetMapping("/request")
    public ApiResponse<?> pay(HttpServletRequest request) {
        return paymentService.createVnPayPayment(request);
    }

    @Override
    @PostMapping("/vn-pay-callback")
    public ApiResponse<?> payCallBack(
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("vnp_TransactionStatus") String transactionStatus,
            @RequestParam("vnp_Amount") Long amount,
            @RequestParam("vnp_OrderInfo") String transactionType,
            @RequestParam("vnp_PayDate") String payDate,
            @RequestParam("vnp_TxnRef") String txnRef,
            @RequestParam("vnp_TransactionNo") String transactionNo
    ) throws ParseException {
        if (responseCode.equals("00") && transactionStatus.equals("00")){
            return paymentService.saveTransaction(
                    txnRef, amount, transactionType, payDate, transactionNo
            );
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed", Boolean.FALSE);
    }
}
