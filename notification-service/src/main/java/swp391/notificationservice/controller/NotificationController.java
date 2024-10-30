package swp391.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.ApiResponse;
import swp391.notificationservice.dto.response.NotificationFeign;
import swp391.notificationservice.service.INotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController implements INotificationController {

    private final INotificationService notificationService;


    @Override
    @PostMapping("/send/report")
    public Boolean sendReportNotification(@RequestBody NotificationRequest request) {
        return notificationService.sendReportNotification(request);
    }

    @Override
    @PostMapping("/send/verification")
    public Boolean sendVerificationNotification(
            @RequestBody NotificationRequest notiRequest
    ) {
        return notificationService.sendVerificationNotification(notiRequest);
    }

    @Override
    @PostMapping("/send/cancel-order")
    public Boolean sendCancelOrderNotification(
            @RequestBody NotificationRequest notiRequest
    ) {
        return notificationService.sendCancelOrderNotification(notiRequest);
    }

    @Override
    @PostMapping("/send/order-ticket")
    public Boolean sendOrderTicketNotification(
            @RequestBody NotificationRequest notiRequest
    ) {
        return notificationService.sendOrderTicketNotification(notiRequest);
    }

    @Override
    @GetMapping("/get-all/receiver/{receiverId}")
    public List<NotificationFeign> getAllNotificationOfReceiver(
            @PathVariable("receiverId") Long receiverId
    ) {
        return notificationService.getAllNotificationOfReceiver(receiverId);
    }

    @Override
    @PutMapping("/mark-read/{id}")
    public ApiResponse<?> markReadNotification(@PathVariable("id") String ojectIdString) {
        return notificationService.markReadNotification(ojectIdString);
    }

    @Override
    @PutMapping("/mark-deleted/{id}")
    public ApiResponse<?> markDeletedNotification(@PathVariable("id") String ojectIdString) {
        return notificationService.markDeletedNotification(ojectIdString);
    }

    @Override
    @DeleteMapping("/delete-forever/{id}")
    public ApiResponse<?> deleteForever(@PathVariable("id") String ojectIdString) {
        return notificationService.deleteForever(ojectIdString);
    }

    @Override
    @GetMapping("/have-notifications/{receiverId}")
    public ApiResponse<Boolean> haveNotification(@PathVariable("receiverId") Long receiverId) {
        return notificationService.haveNotification(receiverId);
    }

}
