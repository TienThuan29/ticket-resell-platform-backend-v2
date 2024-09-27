package swp391.userservice.dto.reponse;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class SellerResponse {
    @JsonProperty("id") private Long id;

    @JsonProperty("username") private String username;

    @JsonProperty("firstname") private String firstname;

    @JsonProperty("lastname") private String lastname;

    @JsonProperty("avatar") byte[] avatar;

    @JsonProperty("email") private String email;
}
