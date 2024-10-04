package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericTicketFilter {

    @JsonProperty("minPrice")
    private Long minPrice;

    @JsonProperty("maxPrice")
    private Long maxPrice;

    @JsonProperty("ticketName")
    private String ticketName;

    @JsonProperty("area")
    private String area;

    @JsonProperty("isPaper")
    private boolean isPaper;
}
