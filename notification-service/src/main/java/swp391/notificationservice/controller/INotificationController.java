package swp391.notificationservice.controller;

import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.ApiResponse;
import swp391.notificationservice.dto.response.NotificationResponse;

import java.util.List;

public interface INotificationController {

    Boolean sendVerificationNotification(NotificationRequest notiRequest);

    ApiResponse<List<NotificationResponse>> getAllNotificationOfReceiver(Long receiverId);
}
