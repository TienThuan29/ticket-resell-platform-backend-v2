package swp391.chatservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import swp391.chatservice.dto.reponse.ApiResponse;
import swp391.chatservice.entity.Message;
import swp391.chatservice.entity.RoomChat;
import swp391.chatservice.exception.def.NotFoundException;
import swp391.chatservice.repository.RoomChatRepository;
import swp391.chatservice.service.def.IChatService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ChatService implements IChatService {

    private final RoomChatRepository roomChatRepo;

    @Override
    public ApiResponse<?> getOrCreateRoomChat(Long member1, Long member2) {
        return new ApiResponse<>(HttpStatus.OK, "",
                roomChatRepo.findByTwoMembers(member1, member2).orElseGet(() -> createRoomChat(member1, member2)));
    }

    private RoomChat createRoomChat(Long member1, Long member2){
        return RoomChat.builder()
                .conversationId(generateConversationId(6))
                .members(List.of(member1, member2))
                .time(getPresentDate())
                .messages(new ArrayList<>())
                .build();
    }

    private Date getPresentDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

    public Long generateConversationId(int length) {
        String characters = "0123456789"; //ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        do{
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(characters.length());
                sb.append(characters.charAt(randomIndex));
            }
        }while (roomChatRepo.findByConversationId(Long.parseLong(sb.toString())).isPresent());
        return Long.parseLong(sb.toString());
    }

    @Override
    public Message saveMessage(Long conversationId, Message message) {
        RoomChat roomChat = roomChatRepo.findByConversationId(conversationId)
                .orElseThrow(() -> new NotFoundException(""));

        if (!roomChat.getMembers().contains(message.getSenderId()))
            throw new NotFoundException("");

        message.setTime(getPresentDate());
        roomChat.getMessages().add(message);
        roomChatRepo.save(roomChat);
        return message;
    }
}
