package swp391.reportservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import swp391.entity.ReportType;
import swp391.entity.Ticket;
import swp391.entity.User;

@Data
@Builder
public class ReportFraudResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("proof")
    private byte[] proof;

    @JsonProperty("process")
    private String process;

    @JsonProperty("note")
    private String note;

    @JsonProperty("reportType")
    private ReportTypeResponse reportType;

    @JsonProperty("staffId")
    private Long staffId;

    @JsonProperty("reportedUser")
    private UserResponse reportedUser;

    @JsonProperty("ticket")
    private TicketResponse ticket;

    @JsonProperty("accuser")
    private UserResponse accuser;
}
