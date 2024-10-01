package swp391.emailservice.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import swp391.entity.fixed.VerificationType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("startTime")
    private Long startTime;

    @JsonProperty("verificationCode")
    private String verificationCode;

    @JsonProperty("verificationType")
    private VerificationType verificationType;
}
