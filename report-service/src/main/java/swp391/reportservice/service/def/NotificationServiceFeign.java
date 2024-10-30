package swp391.reportservice.service.def;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import swp391.reportservice.dto.request.NotificationRequest;

@FeignClient(name = "notification-service", url = "http://localhost:9011/api/notifications")
public interface NotificationServiceFeign {

    @PostMapping("/send/report")
    Boolean sendReportNotification(@RequestBody NotificationRequest notiRequest);

}
