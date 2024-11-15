
package swp391.ticketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyerResponse {
    @JsonProperty("id") private Long id;

    @JsonProperty("username") private String username;

    @JsonProperty("firstname") private String firstname;

    @JsonProperty("lastname") private String lastname;

    @JsonProperty("avatar") byte[] avatar;

    @JsonProperty("email") private String email;
}

