package swp391.adminservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyUpdatingRequest {

    @JsonProperty("fee")
    private Integer fee;

    @JsonProperty("content")
    private String content;

}
