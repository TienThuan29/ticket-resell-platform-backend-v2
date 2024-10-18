package swp391.staffservice.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    private Long senderId;

    private Long receiverId;

    private String header;

    private String subHeader;

    private String content;

}