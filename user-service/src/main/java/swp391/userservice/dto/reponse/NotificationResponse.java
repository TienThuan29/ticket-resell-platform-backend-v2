package swp391.userservice.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("sentDate")
    private Date sentDate;

    @JsonProperty("sender")
    private SenderResponse sender;

    @JsonProperty("receiver")
    private UserDTO receiver;

    @JsonProperty("header")
    private String header;

    @JsonProperty("subHeader")
    private String subHeader;

    @JsonProperty("content")
    private String content;

    @JsonProperty("isRead")
    private Boolean isRead;

    @JsonProperty("isDeleted")
    private Boolean isDeleted;

    @JsonProperty("type")
    private String type;

}
