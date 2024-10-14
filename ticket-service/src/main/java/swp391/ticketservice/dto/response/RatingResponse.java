package swp391.ticketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {

    @JsonProperty("buyer")
    private BuyerResponse buyer;

    @JsonProperty("genericTicket")
    private GenericTicketResponse genericTicket;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("stars")
    private Short stars;
}
