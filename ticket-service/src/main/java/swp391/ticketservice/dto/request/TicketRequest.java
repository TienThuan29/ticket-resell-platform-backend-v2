package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Author: Nguyen Nhat Truong
 */
@Data
@Builder
public class TicketRequest {
    @JsonProperty("ticketSerial")
    @NotEmpty(message = "Số seri của vé không được phép trống")
    private String ticketSerial;

    @JsonProperty("image")
    private byte[] image;

    @JsonProperty("note")
    private String note;

    @JsonProperty("genericTicketId")
    private Long genericTicketId;
}
