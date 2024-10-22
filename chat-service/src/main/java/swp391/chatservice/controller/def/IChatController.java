package swp391.chatservice.controller.def;

import swp391.chatservice.dto.reponse.ApiResponse;
import swp391.chatservice.entity.Message;

public interface IChatController {

    ApiResponse<?> getOrCreateRoom(Long firstMemberId, Long secondMemberId);

    void sendMessage(Long conversationId, Message message);
}
