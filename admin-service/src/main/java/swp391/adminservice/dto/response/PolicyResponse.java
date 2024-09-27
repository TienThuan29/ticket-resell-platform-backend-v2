package swp391.adminservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import swp391.entity.TypePolicy;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("content")
    private List<String> content;

    @JsonProperty("fee")
    private Integer fee;

    @JsonProperty("typePolicy")
    private TypePolicy typePolicy;

}
