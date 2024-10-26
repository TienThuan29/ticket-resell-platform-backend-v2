package swp391.chatservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Message {

    @JsonProperty("senderId")
    private Long senderId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("time")
    private Date time;
}
