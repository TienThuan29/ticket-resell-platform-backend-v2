package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashtagRequest {

    @JsonProperty("name")
    private String name;

}
