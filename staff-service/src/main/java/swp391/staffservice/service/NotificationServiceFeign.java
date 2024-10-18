package swp391.staffservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import swp391.staffservice.dto.request.NotificationRequest;
import swp391.staffservice.dto.response.ApiResponse;

@FeignClient(name = "notification-service", url = "http://localhost:9011/api/notifications")
public interface NotificationServiceFeign {

    @PostMapping("/send/verification")
    Boolean sendVerificationNotification(@RequestBody NotificationRequest notiRequest);

}
