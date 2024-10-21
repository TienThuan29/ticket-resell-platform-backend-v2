package swp391.chatservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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
    public ApiResponse<?> getOrCreateRoomChat(Long firstMemberId, Long secondMemberId) {
        return new ApiResponse<>(HttpStatus.OK, "",
                roomChatRepo.findByTwoMembers(firstMemberId, secondMemberId)
                        .orElseGet(() -> createRoomChat(firstMemberId, secondMemberId)));
    }

    private RoomChat createRoomChat(Long firstMemberId, Long secondMemberId){
        Long conversationId = generateConversationId(6);
        var roomChat = RoomChat.builder()
                .id(new ObjectId())
                .conversationId(conversationId)
                .members(List.of(firstMemberId, secondMemberId))
                .time(getPresentDate())
                .messages(new ArrayList<>())
                .build();
        return roomChatRepo.save(roomChat);
    }

    private Date getPresentDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

    public Long generateConversationId(int length) {
        String characters = "123456789"; //ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
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
