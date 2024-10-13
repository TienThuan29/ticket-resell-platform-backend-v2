package swp391.paymentsservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.Transaction;
import swp391.paymentsservice.dto.response.TransactionResponse;

@Component
public class TransactionMapper {

    public TransactionResponse toResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .transactionNo(transaction.getTransactionNo())
                .amount(transaction.getAmount())
                .isDone(transaction.getIsDone())
                .transDate(transaction.getTransDate())
                .type(transaction.getType().content)
                .build();
    }
}
