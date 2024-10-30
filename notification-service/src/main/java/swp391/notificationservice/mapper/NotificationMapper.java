package swp391.notificationservice.mapper;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.NotificationFeign;
import swp391.notificationservice.entity.Notification;
import swp391.notificationservice.entity.NotificationType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class NotificationMapper {

    public NotificationFeign toResponse(Notification notification){
        return NotificationFeign.builder()
                .id(notification.getId().toString())
                .sentDate(notification.getSentDate())
                .senderId(notification.getSenderId())
                .receiverId(notification.getReceiverId())
                .header(notification.getHeader())
                .subHeader(notification.getSubHeader())
                .content(notification.getContent())
                .isRead(notification.getIsRead())
                .isDeleted(notification.getIsDeleted())
                .type(notification.getType().name())
                .build();
    }

    public Notification toReportNotification(NotificationRequest request) {
        return this.toNotificationFromType(request, NotificationType.REPORT);
    }

    public Notification toVerificationNotification(NotificationRequest request) {
        return this.toNotificationFromType(request, NotificationType.VERIFICATION);
    }

    public Notification toCancelOrderNotification(NotificationRequest request) {
        return this.toNotificationFromType(request, NotificationType.CANCEL_ORDER);
    }

    public Notification toOrderTicketNotification(NotificationRequest request) {
        return this.toNotificationFromType(request, NotificationType.ORDER);
    }

    private Notification toNotificationFromType(NotificationRequest request, NotificationType type) {
        Date sentDateNow = getNowTime();
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
                .type(type)
                .build();
    }

    private Date getNowTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
       return Date.from(zonedDateTime.toInstant());
    }
}
