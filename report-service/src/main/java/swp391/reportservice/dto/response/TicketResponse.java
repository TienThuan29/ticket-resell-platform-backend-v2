package swp391.reportservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import swp391.entity.fixed.GeneralProcess;

import java.util.Date;

/**
 * Author: Nguyen Nhat Truong
 */
@Data
@Builder
public class TicketResponse {

    @JsonProperty("ticketId") private Long ticketId;

    @JsonProperty("ticketSerial") private String ticketSerial;

    @JsonProperty("image") private byte[] image;

    @JsonProperty("isChecked") private boolean isChecked;

    @JsonProperty("isBought") private boolean isBought;

    @JsonProperty("isValid") private boolean isValid;

    @JsonProperty("note") private String note;

    @JsonProperty("process") private GeneralProcess process;

    @JsonProperty("boughtDate") private Date boughtDate;

    @JsonProperty("genericTicketId") private Long genericTicketId;

    @JsonProperty("buyerId") private Long buyerId;

    @JsonProperty("verifyStaffId") private Long verifyStaffId;

//    @JsonProperty("genericTicketObject") private GenericTicketResponse genericTicketObject;
}
