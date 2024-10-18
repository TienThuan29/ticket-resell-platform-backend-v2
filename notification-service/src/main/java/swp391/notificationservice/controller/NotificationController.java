package swp391.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.ApiResponse;
import swp391.notificationservice.dto.response.NotificationResponse;
import swp391.notificationservice.service.INotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController implements INotificationController {

    private final INotificationService notificationService;

    @Override
    @PostMapping("/send/verification")
    public Boolean sendVerificationNotification(
            @RequestBody NotificationRequest notiRequest
    ) {
        return notificationService.sendVerificationNotification(notiRequest);
    }

    @Override
    @GetMapping("/get-all/receiver/{receiverId}")
    public ApiResponse<List<NotificationResponse>> getAllNotificationOfReceiver(
            @PathVariable("receiverId") Long receiverId
    ) {
        return null;
    }

}
