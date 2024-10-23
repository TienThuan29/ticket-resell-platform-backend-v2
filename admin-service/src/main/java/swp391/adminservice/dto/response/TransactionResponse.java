package swp391.adminservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import swp391.entity.User;
import swp391.entity.fixed.TransactionType;

import java.util.Date;

@Data
@Builder
public class TransactionResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("trans_date")
    private Date transDate;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("is_done")
    private Boolean isDone;

    @JsonProperty("transaction_no")
    private String transactionNo;

    @JsonProperty("user")
    private User user;

    @JsonProperty("trans_type")
    private TransactionType type;
}
