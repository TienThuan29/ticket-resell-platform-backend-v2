package swp391.userservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.userservice.dto.reponse.NotificationFeign;
import swp391.userservice.dto.reponse.NotificationResponse;
import swp391.userservice.dto.reponse.SenderResponse;
import swp391.userservice.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public NotificationResponse fromFeignToResponse(NotificationFeign notificationFeign) {
        var receiver = userRepository.findById(notificationFeign.getReceiverId()).get();
        return NotificationResponse.builder()
                .id(notificationFeign.getId())
                .sentDate(notificationFeign.getSentDate())
                .sender(
                        SenderResponse.builder().id(notificationFeign.getSenderId()).build()
                )
                .receiver(userMapper.toUserDTO(receiver))
                .header(notificationFeign.getHeader())
                .subHeader(notificationFeign.getSubHeader())
                .content(notificationFeign.getContent())
                .type(notificationFeign.getType())
                .isRead(notificationFeign.getIsRead())
                .isDeleted(notificationFeign.getIsDeleted())
                .build();
    }

}
