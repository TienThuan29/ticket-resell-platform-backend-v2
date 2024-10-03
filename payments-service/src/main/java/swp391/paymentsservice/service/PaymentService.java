package swp391.paymentsservice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import swp391.entity.Transaction;
import swp391.entity.User;
import swp391.entity.fixed.TransactionType;
import swp391.paymentsservice.config.PaymentConfiguration;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.repository.TransactionRepository;
import swp391.paymentsservice.repository.UserRepository;
import swp391.paymentsservice.utils.VnPayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentService implements IPaymentService{

    private final PaymentConfiguration vnPayConfig;

    private final UserRepository userRepo;

    private final TransactionRepository transactionRepo;

    @Override
    public ApiResponse<?> createVnPayPayment(HttpServletRequest request) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String orderInfo = request.getParameter("orderInfo");
        String customerCode= request.getParameter("customerCode");

        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_IpAddr", VnPayUtils.getIpAddress(request));
        vnpParamsMap.put("vnp_OrderInfo", orderInfo);
        vnpParamsMap.put("vnp_TxnRef", vnpParamsMap.get("vnp_TxnRef")+customerCode);
        //build query url
        String queryUrl = VnPayUtils.getPaymentURL(vnpParamsMap, true);
        String hashData = VnPayUtils.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VnPayUtils.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;

        return new ApiResponse<>(HttpStatus.OK, "Success", paymentUrl);
    }

    @Override
    public ApiResponse<?> saveTransaction(
            String vnp_TxnRef,
            Long amount,
            String transactionType,
            String payDate
    ) throws ParseException {
        String customerCode= vnp_TxnRef.substring(8);
        var user= userRepo.findByCustomerCode(customerCode).get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = formatter.parse(payDate);
        amount /=100;

        Transaction transaction= Transaction.builder()
                .amount(amount)
                .type(TransactionType.valueOf(transactionType))
                .user(user)
                .isDone(true)
                .transDate(date)
                .build();
        Long balance= user.getBalance()+amount;
        user.setBalance(balance);
        userRepo.save(user);
        transactionRepo.save(transaction);

        return new ApiResponse<>(HttpStatus.OK, "Success", Boolean.TRUE);
    }
}
