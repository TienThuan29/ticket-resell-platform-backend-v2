package swp391.userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}
