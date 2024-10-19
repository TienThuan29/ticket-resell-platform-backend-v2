package swp391.userservice.dto.reponse;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SenderResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private String role;

}
