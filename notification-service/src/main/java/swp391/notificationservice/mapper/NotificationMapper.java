package swp391.notificationservice.mapper;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.NotificationResponse;
import swp391.notificationservice.entity.Notification;
import swp391.notificationservice.entity.NotificationType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .sentDate(notification.getSentDate())
                .senderId(notification.getSenderId())
                .receiverId(notification.getReceiverId())
                .header(notification.getHeader())
                .subHeader(notification.getSubHeader())
                .content(notification.getContent())
                .isRead(notification.getIsRead())
                .isDeleted(notification.getIsDeleted())
                .type(notification.getType())
                .build();
    }

    public Notification toVerificationNotification(NotificationRequest request) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date sentDateNow = Date.from(zonedDateTime.toInstant());

        return Notification.builder()
                .id(new ObjectId())
                .sentDate(sentDateNow)
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .header(request.getHeader())
                .subHeader(request.getSubHeader())
                .content(request.getContent())
                .isRead(Boolean.FALSE)
                .isDeleted(Boolean.FALSE)
                .type(NotificationType.VERIFICATION)
                .build();
    }

}
