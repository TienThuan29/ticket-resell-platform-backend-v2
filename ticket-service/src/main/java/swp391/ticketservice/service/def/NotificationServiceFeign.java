package swp391.ticketservice.service.def;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import swp391.ticketservice.dto.request.NotificationRequest;

@FeignClient(name = "notification-service", url = "http://localhost:9011/api/notifications")
public interface NotificationServiceFeign {

    @PostMapping("/send/cancel-order")
    Boolean sendCancelOrderNotification(@RequestBody NotificationRequest notiRequest);

    @PostMapping("/send/order-ticket")
    Boolean sendOrderTicketNotification(@RequestBody NotificationRequest notiRequest);

    @PostMapping("/send/accept-to-sell")
    Boolean sendAcceptToSellNotification(@RequestBody NotificationRequest notiRequest);

    @PostMapping("/send/reject-to-sell")
    Boolean sendRejectToSellNotification(@RequestBody NotificationRequest notiRequest);
}
