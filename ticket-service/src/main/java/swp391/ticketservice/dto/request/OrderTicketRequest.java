package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTicketRequest {

    @JsonProperty("genericTicketId")
    private Long genericTicketId;

    @JsonProperty("buyerId")
    private Long buyerId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("paymentMethodId")
    private Long paymentMethodId;

    @JsonProperty("totalPrice")
    private Long totalPrice;

    @JsonProperty("isPaper")
    private Boolean isPaper;
}
