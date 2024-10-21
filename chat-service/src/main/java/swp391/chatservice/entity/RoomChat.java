package swp391.chatservice.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "room_chat")
public class RoomChat {

    @Id
    private ObjectId id;

    @Field(name = "conversation_id")
    private Long conversationId;

    @Field(name = "time")
    private Date time;

    @Field(name = "members")
    private List<Long> members;

    @Field(name = "messages")
    private List<Message> messages;
}
