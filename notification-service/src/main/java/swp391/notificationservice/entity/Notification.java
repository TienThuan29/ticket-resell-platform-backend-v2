package swp391.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@Document(collection = "notifications")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    private ObjectId id;

    @Field(name = "sent_date")
    private Date sentDate;

    @Field(name = "sender_id")
    private Long senderId;

    @Field(name = "receiver_id")
    private Long receiverId;

    @Field(name = "header")
    private String header;

    @Field(name = "sub_header")
    private String subHeader;

    @Field(name = "content")
    private String content;

    @Field(name = "is_read")
    private Boolean isRead;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    @Field(name = "type")
    private NotificationType type;

}
