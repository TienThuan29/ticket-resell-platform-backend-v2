package swp391.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "notification-service", url = "http://localhost:9011/api/notifications")
public interface NotificationServiceFeign {

    @GetMapping("/get-all/receiver/{receiverId}")
    Object getAllNotificationOfReceiver(@PathVariable("receiverId") Long receiverId);

}
