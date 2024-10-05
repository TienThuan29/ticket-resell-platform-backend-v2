package swp391.adminservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import swp391.entity.fixed.Role;

@Getter
@Setter
@Builder
public class StaffDTO {

    @JsonProperty("id") private Long id;

    @JsonProperty("username") private String username;

    @JsonProperty("firstname") private String firstname;

    @JsonProperty("lastname") private String lastname;

    @JsonProperty("email") private String email;

    @JsonProperty("phone") private String phone;

    @JsonProperty("isEnable") private Boolean isEnable;

    @JsonProperty("roleCode") private Role roleCode;

    @JsonProperty("balance") private Long balance;

    @JsonProperty("revenue") private Long revenue;

    @JsonProperty("staffCode") private String staffCode;

    @JsonProperty("avatar") private byte[] avatar;

}
