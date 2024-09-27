package swp391.userservice.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import swp391.entity.fixed.Role;

/**
 * Author: Nguyen Tien Thuan
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty("id") private Long id;

    @JsonProperty("username") private String username;

    @JsonProperty("firstname") private String firstname;

    @JsonProperty("lastname") private String lastname;

    @JsonProperty("avatar") byte[] avatar;

    @JsonProperty("email") private String email;

    @JsonProperty("phone") private String phone;

    @JsonProperty("isEnable") private Boolean isEnable;

    @JsonProperty("roleCode") private Role roleCode;

    @JsonProperty("balance") private Long balance;

    @JsonProperty("revenue") private Long revenue;

    @JsonProperty("customerCode") private String customerCode;

    @JsonProperty("isSeller") private Boolean isSeller;

}
