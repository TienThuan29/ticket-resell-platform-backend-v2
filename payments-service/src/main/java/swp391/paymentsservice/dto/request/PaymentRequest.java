package swp391.paymentsservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    @JsonProperty("customerCode")
    private String customerCode;
    @JsonProperty("amount")
    private Long amount;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("payDate")
    private String orderInfo;
}
