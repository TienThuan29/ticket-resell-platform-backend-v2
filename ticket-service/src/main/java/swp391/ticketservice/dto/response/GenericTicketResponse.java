package swp391.ticketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import swp391.entity.Ticket;

import java.util.Date;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 */
@Data
@Builder
public class GenericTicketResponse {

    @JsonProperty("id") private Long id;

    @JsonProperty("ticketName") private String ticketName;

    @JsonProperty("price") private Long price;

    @JsonProperty("salePercent") private Double salePercent;

    @JsonProperty("area") private String area;

    @JsonProperty("expiredDateTime") private Date expiredDateTime;

    @JsonProperty("description") private String description;

    @JsonProperty("linkEvent") private String linkEvent;

    @JsonProperty("isPaper") private boolean isPaper;

    @JsonProperty("category") private CategoryResponse category;

    @JsonProperty("event") private EventResponse event;

    @JsonProperty("seller") private SellerResponse seller;

    @JsonProperty("tickets") private List<TicketResponse> tickets;

    @JsonProperty("stars") private Short star;

    @JsonProperty("comment") private String comment;
}
