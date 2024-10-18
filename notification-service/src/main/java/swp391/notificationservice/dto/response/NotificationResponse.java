package swp391.notificationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import swp391.notificationservice.entity.NotificationType;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    @JsonProperty("id")
    private ObjectId id;

    @JsonProperty("sentDate")
    private Date sentDate;

    @JsonProperty("senderId")
    private Long senderId;

    @JsonProperty("receiverId")
    private Long receiverId;

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
    private NotificationType type;

}
