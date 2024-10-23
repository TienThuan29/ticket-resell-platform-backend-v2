package swp391.adminservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    @JsonProperty("id") private Long id;

    @JsonProperty("username") private String username;

    @JsonProperty("firstname") private String firstname;

    @JsonProperty("lastname") private String lastname;

    @JsonProperty("avatar") byte[] avatar;

    @JsonProperty("email") private String email;

    @JsonProperty("phone") private String phone;

    @JsonProperty("balance") private Long balance;

    @JsonProperty("revenue") private Long revenue;

    @JsonProperty("is_enable") private boolean isEnable;

    @JsonProperty("is_seller") private boolean isSeller;
}
