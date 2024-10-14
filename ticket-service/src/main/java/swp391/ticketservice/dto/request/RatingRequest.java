package swp391.ticketservice.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    @JsonProperty("buyerId")
    private Long buyerId;

    @JsonProperty("genericTicketId")
    private Long genericTicketId;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("stars")
    private Short stars;
}
