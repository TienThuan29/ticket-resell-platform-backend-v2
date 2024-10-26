package swp391.paymentsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import swp391.entity.*;
import swp391.entity.fixed.GeneralProcess;
import swp391.entity.fixed.Role;
import swp391.entity.fixed.TransactionType;
import swp391.paymentsservice.config.MessageConfiguration;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.TransactionResponse;
import swp391.paymentsservice.exception.def.NotFoundException;
import swp391.paymentsservice.mapper.TransactionMapper;
import swp391.paymentsservice.repository.*;
import swp391.paymentsservice.service.def.ITransactionService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepo;

    private final UserRepository userRepo;

    private final ReportFraudRepository reportFraudRepo;

    private final GenericTicketRepository genericTicketRepo;

    private final MessageConfiguration messageConfig;

    private final TransactionMapper transactionMapper;

    private final PolicyRepository policyRepo;

    private final TypePolicyRepository typePolicyRepo;

    private final StaffRepository staffRepo;


    @Override
    public ApiResponse<List<TransactionResponse>> getAllTransactionByUser(Long id) {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                transactionRepo.findByUserId(id).stream().map(transactionMapper::toResponse)
                        .sorted(Comparator.comparing(TransactionResponse::getTransDate).reversed())
                        .toList()
        );
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getTransDepositByUserId(Long id) {
        var user= userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_USER+" :"+id));

        var transDeposit= transactionRepo.findByUserAndType(user, TransactionType.DEPOSIT);

        var transDepositResponse= transDeposit.stream()
                .map(transactionMapper::toResponse)
                .sorted(Comparator.comparing(TransactionResponse::getTransDate))
                .toList();
        return new ApiResponse<>(HttpStatus.OK, "", transDepositResponse);
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getTransWithdrawalByUserId(Long id) {
        var user= userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_USER+" :"+id));

        var transDeposit= transactionRepo.findByUserAndType(user, TransactionType.WITHDRAWAL);

        var transDepositResponse= transDeposit.stream()
                .map(transactionMapper::toResponse)
                .sorted(Comparator.comparing(TransactionResponse::getTransDate))
                .toList();
        return new ApiResponse<>(HttpStatus.OK, "", transDepositResponse);
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getTransSellingByUserId(Long id) {
        var user= userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_USER+" :"+id));

        var transDeposit= transactionRepo.findByUserAndType(user, TransactionType.SELLING);

        var transDepositResponse= transDeposit.stream()
                .map(transactionMapper::toResponse)
                .sorted(Comparator.comparing(TransactionResponse::getTransDate))
                .toList();
        return new ApiResponse<>(HttpStatus.OK, "", transDepositResponse);
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getTransBuyingByUserId(Long id) {
        var user= userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_USER+" :"+id));

        var transDeposit= transactionRepo.findByUserAndType(user, TransactionType.BUYING);

        var transDepositResponse= transDeposit.stream()
                .map(transactionMapper::toResponse)
                .sorted(Comparator.comparing(TransactionResponse::getTransDate))
                .toList();
        return new ApiResponse<>(HttpStatus.OK, "", transDepositResponse);
    }

    //Automation refund and transfer money to seller
    @Scheduled(fixedDelay = 7200000) // 7200000 milliseconds = 2 hours
    public void checkTransaction(){
//        List<Transaction> transactionRefundAndReport = new ArrayList<>();

        // Get generic ticket is expired 3 days
        var genericTickets = genericTicketRepo.findAll().stream()
                        .filter(genericTicket ->
                                isExpiredMoreThanThreeDays(genericTicket.getExpiredDateTime()))
                        .toList();

        // Get transaction is selling and not done
        var transactions = transactionRepo.getByGenericTickets(genericTickets);

        // Get admin account
        var admin = staffRepo.findByRoleCode(Role.ADMIN).get(0);

        long revenue = admin.getRevenue();
        long balance = admin.getBalance();

        for (Transaction transaction :transactions){
            transaction.setIsDone(true);
            long priceAfterFee = priceAfterFee(transaction.getAmount());
            long profit = transaction.getAmount() - priceAfterFee;

            saveBalance(transaction.getUser(), priceAfterFee, true);
            saveRevenue(transaction.getUser(), priceAfterFee, true);
            revenue += profit;
            balance += profit;
        }
        transactionRepo.saveAll(transactions);
        admin.setRevenue(revenue);
        admin.setBalance(balance);
        staffRepo.save(admin);

        var reports = reportFraudRepo.getReportByGenericTickets(genericTickets, GeneralProcess.REPORT_PROCESSING);
        for (ReportFraud report : reports) {
            // Create transaction add money for accuser
            long ticketPrice = priceAfterFee(getPriceByTicket(report.getTicket()));

            Transaction transactionRefund = Transaction.builder()
                    .transactionNo(randoTransactionNo())
                    .amount(ticketPrice)
                    .type(TransactionType.REFUND)
                    .transDate(getPresentDate())
                    .isDone(Boolean.TRUE)
                    .user(userRepo.findById(report.getAccuser().getId()).get())
                    .build();
            transactionRepo.save(transactionRefund);
            saveBalance(report.getAccuser(), ticketPrice, true);

            // Create transaction minus money of seller
            var reportedUser = userRepo.findById(report.getReportedUserId())
                    .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_REPORTED_USER));
            Transaction transactionReported = Transaction.builder()
                    .transactionNo(randoTransactionNo())
                    .amount(ticketPrice)
                    .type(TransactionType.REPORTED)
                    .transDate(getPresentDate())
                    .isDone(Boolean.TRUE)
                    .user(reportedUser)
                    .build();
            saveBalance(reportedUser, ticketPrice, false);
            saveRevenue(reportedUser, ticketPrice, false);
            transactionRepo.save(transactionReported);

            report.setProcess(GeneralProcess.SUCCESS);
        }
        reportFraudRepo.saveAll(reports);
    }

    private long priceAfterFee(Long price){
        var typePolicy = typePolicyRepo.findById(1L)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_TYPE_POLICY));

        var policy = policyRepo.findByTypePolicy(typePolicy)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_POLICY));

        return price - price*policy.getFee()/100;
    }

    private boolean isExpiredMoreThanThreeDays(Date expiredDate){
        Date presentDate = getPresentDate();
        if(expiredDate.before(presentDate)){
            long periodMilli= Math.abs(expiredDate.getTime()-presentDate.getTime());
            // 259200000 milliseconds = 3 days
            return periodMilli > 259200000;
        }
        return false;
    }

    private Date getPresentDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

    private long getPriceByTicket(Ticket ticket){
        GenericTicket genericTicket= ticket.getGenericTicket();
        return (long) (genericTicket.getPrice() - genericTicket.getPrice()*genericTicket.getSalePercent()/100);
    }

    private void saveBalance(User user, long amount, boolean isAdd){
        User saveUser = userRepo.findById(user.getId()).get();
        long newBalance = 0;
        if (isAdd){
            newBalance= saveUser.getBalance() + amount;
        }else {
            newBalance= saveUser.getBalance() - amount;
        }
        saveUser.setBalance(newBalance);
        userRepo.save(saveUser);
    }

    private void saveRevenue(User user, long amount, boolean isAdd){
        User saveUser = userRepo.findById(user.getId()).get();
        long newRevenue = 0;
        if (isAdd){
            newRevenue= saveUser.getRevenue() + amount;
        }else {
            newRevenue= saveUser.getRevenue() - amount;
        }
        saveUser.setBalance(newRevenue);
        userRepo.save(saveUser);
    }

    private String randoTransactionNo() {
        String transactionNo= "";

        do {
            transactionNo= UUID.randomUUID().toString().substring(1,8) + UUID.randomUUID().toString().substring(1,3);
        }while (transactionRepo.findByTransactionNo(transactionNo).isPresent());

        return transactionNo;
    }
}
