package swp391.paymentsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import swp391.entity.Transaction;
import swp391.entity.fixed.TransactionType;
import swp391.paymentsservice.config.MessageConfiguration;
import swp391.paymentsservice.dto.response.ApiResponse;
import swp391.paymentsservice.dto.response.TransactionResponse;
import swp391.paymentsservice.exception.def.NotFoundException;
import swp391.paymentsservice.mapper.TransactionMapper;
import swp391.paymentsservice.repository.TransactionRepository;
import swp391.paymentsservice.repository.UserRepository;
import swp391.paymentsservice.service.def.ITransactionService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepo;

    private final UserRepository userRepo;

    private final MessageConfiguration messageConfig;

    private final TransactionMapper transactionMapper;

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
}
