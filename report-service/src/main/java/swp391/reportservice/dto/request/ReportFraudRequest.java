package swp391.reportservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import swp391.entity.ReportType;
@Data
@Builder
public class ReportFraudRequest {
    @JsonProperty("proof")
    private byte[] proof;

    @JsonProperty("content")
    @NotEmpty(message = "Cần mô tả nội dung báo cáo")
    private String content;

    @NotNull
    @JsonProperty("reportTypeId")
    private Integer reportTypeId;

    @NotNull
    @JsonProperty("staffId")
    private Long staffId;

    @NotNull
    @JsonProperty("ticketId")
    private Long ticketId;

    @NotNull
    @JsonProperty("accuserId")
    private Long accuserId;
}
