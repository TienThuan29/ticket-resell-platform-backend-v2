package swp391.chatservice.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Message {

    private Long senderId;

    private String message;

    private Date time;
}
