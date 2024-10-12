package swp391.ticketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTicketResponse {

    @JsonProperty("genericTicket")
    private GenericTicketResponse genericTicket;

    @JsonProperty("paymentMethod")
    private PaymentMethodResponse paymentMethod;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("totalPrice")
    private Long totalPrice;

    @JsonProperty("isAccepted")
    private Boolean isAccepted;

    @JsonProperty("note")
    private String note;

    @JsonProperty("buyer")
    private BuyerResponse buyer;

    @JsonProperty("ticketList")
    private List<TicketResponse> ticketList;

}
