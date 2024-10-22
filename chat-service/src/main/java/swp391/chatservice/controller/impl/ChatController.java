package swp391.chatservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swp391.chatservice.controller.def.IChatController;
import swp391.chatservice.dto.reponse.ApiResponse;
import swp391.chatservice.entity.Message;
import swp391.chatservice.service.def.IChatService;

@RestController
@RequiredArgsConstructor
public class ChatController implements IChatController {

    private final SimpMessagingTemplate messagingTemplate;

    private final IChatService chatService;

    @Override
    @PostMapping("/api/chat/get-room")
    public ApiResponse<?> getOrCreateRoom(@RequestParam Long firstMemberId, @RequestParam Long secondMemberId) {
        return chatService.getOrCreateRoomChat(firstMemberId, secondMemberId);
    }

    @Override
    @MessageMapping("/chat.sendMessage/{conversationId}")
    public void sendMessage(@DestinationVariable Long conversationId,
                            @Payload Message message) {
        Message savedMessage = chatService.saveMessage(conversationId, message);
        messagingTemplate.convertAndSend("/room/"+conversationId, savedMessage);
    }
}
