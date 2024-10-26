package swp391.paymentsservice.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import swp391.entity.Transaction;
import swp391.entity.fixed.TransactionType;
import swp391.paymentsservice.config.MessageConfiguration;
import swp391.paymentsservice.config.PaymentConfiguration;
import swp391.paymentsservice.dto.request.WithdrawalRequest;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.repository.TransactionRepository;
import swp391.paymentsservice.repository.UserRepository;
import swp391.paymentsservice.service.def.IPaymentService;
import swp391.paymentsservice.utils.VnPayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentConfiguration vnPayConfig;

    private final UserRepository userRepo;

    private final TransactionRepository transactionRepo;

    private final MessageConfiguration messageConfig;

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
            String payDate,
            String transactionNo
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
                .transactionNo(transactionNo)
                .build();
        Long balance= user.getBalance()+amount;
        user.setBalance(balance);
        if(transactionRepo.findByTransactionNo(transactionNo).isEmpty()){
            userRepo.save(user);
            transactionRepo.save(transaction);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_TRANSACTION, Boolean.TRUE);
    }

    @Override
    public ApiResponse<?> withdrawalAmount(WithdrawalRequest withdrawalRequest) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date withdrawalDate = Date.from(zonedDateTime.toInstant());
        try {
            var user = userRepo.findById(withdrawalRequest.getUserId()).orElseThrow(null);
            if (withdrawalRequest.getAmount() < 10000 || withdrawalRequest.getAmount() % 1000 != 0) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_WITHDRAWAL_AMOUNT);
            }
            if (user.getBalance() < withdrawalRequest.getAmount()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_USER_BALANCE_NOT_ENOUGH);
            }
            // Minus user balance
            user.setBalance(user.getBalance() - withdrawalRequest.getAmount());
            // Save transaction
            userRepo.save(user);
            transactionRepo.save(
                    Transaction.builder()
                            .amount(withdrawalRequest.getAmount())
                            .transDate(withdrawalDate)
                            .isDone(Boolean.TRUE)
                            .type(TransactionType.WITHDRAWAL)
                            .user(user)
                            .transactionNo(randomTransactionNo())
                            .build()
            );
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_WITHDRAWAL_AT_SERVER);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_WITHDRAWAL_AMOUNT);
    }

    private String randomTransactionNo() {
        return UUID.randomUUID().toString().substring(1,8) + UUID.randomUUID().toString().substring(1,3);
    }
}
