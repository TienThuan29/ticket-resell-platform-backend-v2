package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptOrDenySellingRequest {

    @JsonProperty("orderNo")
    private String orderNo;

    @JsonProperty("buyerId")
    private Long buyerId;

    @JsonProperty("sellerId")
    private Long sellerId;

    @JsonProperty("genericTicketId")
    private Long genericTicketId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("totalPrice")
    private Long totalPrice;

    @JsonProperty("isAccepted")
    private Boolean isAccepted;

    @JsonProperty("note")
    private String note;
}
