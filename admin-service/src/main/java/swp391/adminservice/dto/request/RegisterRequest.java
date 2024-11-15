package swp391.adminservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @JsonProperty("username")
    @Size(min = 5, max = 50, message = "Tên đăng nhập không hợp lệ")
    private String username;

    @JsonProperty("password")
    @Size(min = 5, max = 100, message = "Mật khẩu không hợp lệ")
    private String password;

    @JsonProperty("email")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @JsonProperty("phone")
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @JsonProperty("firstname")
    @NotBlank(message = "Tên không được để trống")
    private String firstname;

    @JsonProperty("lastname")
    @NotBlank(message = "Tên không được để trống")
    private String lastname;

}
