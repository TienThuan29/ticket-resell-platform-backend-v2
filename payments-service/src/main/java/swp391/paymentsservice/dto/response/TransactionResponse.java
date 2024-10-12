package swp391.paymentsservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import swp391.entity.fixed.TransactionType;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("transDate")
    private Date transDate;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("isDone")
    private Boolean isDone;

    @JsonProperty("transactionNo")
    private String transactionNo;

    @JsonProperty("type")
    private String type;
}
