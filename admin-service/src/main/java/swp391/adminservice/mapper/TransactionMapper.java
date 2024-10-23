package swp391.adminservice.mapper;

import org.springframework.stereotype.Component;
import swp391.adminservice.dto.response.TransactionResponse;
import swp391.entity.Transaction;

@Component
public class TransactionMapper {
    public TransactionResponse toTransactionResponse(Transaction trans){
        return TransactionResponse.builder()
                .transactionNo(trans.getTransactionNo())
                .isDone(trans.getIsDone())
                .id(trans.getId())
                .type(trans.getType())
                .user(trans.getUser())
                .amount(trans.getAmount())
                .transDate(trans.getTransDate())
                .build();
    }
}
