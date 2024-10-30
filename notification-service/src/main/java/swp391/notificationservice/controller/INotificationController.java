package swp391.notificationservice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.ApiResponse;
import swp391.notificationservice.dto.response.NotificationFeign;

import java.util.List;

public interface INotificationController {

    Boolean sendReportNotification(NotificationRequest request);

    Boolean sendVerificationNotification(NotificationRequest notiRequest);

    Boolean sendCancelOrderNotification(NotificationRequest notiRequest);

    Boolean sendOrderTicketNotification(NotificationRequest notiRequest);

    List<NotificationFeign> getAllNotificationOfReceiver(Long receiverId);

    ApiResponse<?> markReadNotification(String ojectIdString);

    ApiResponse<?> markDeletedNotification(String ojectIdString);

    ApiResponse<?> deleteForever(String ojectIdString);

    ApiResponse<Boolean> haveNotification(Long receiverId);
}
