package swp391.chatservice.service.def;

import org.springframework.stereotype.Service;
import swp391.chatservice.dto.reponse.ApiResponse;
import swp391.chatservice.entity.Message;

@Service
public interface IChatService {

    ApiResponse<?> getOrCreateRoomChat(Long member1, Long member2);

    Message saveMessage(Long conversationId, Message message);

}
