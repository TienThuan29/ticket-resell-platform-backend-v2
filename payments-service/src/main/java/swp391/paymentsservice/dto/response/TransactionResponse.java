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

    @JsonProperty("")
    private Long id;

    @JsonProperty("")
    private Date transDate;

    @JsonProperty("")
    private Long amount;

    @JsonProperty("")
    private Boolean isDone;

    @JsonProperty("")
    private String transactionNo;

    @JsonProperty("")
    private String type;
}
