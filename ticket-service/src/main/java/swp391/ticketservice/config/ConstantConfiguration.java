package swp391.ticketservice.config;

import org.springframework.stereotype.Component;

@Component("ticketServiceConstantConfiguration")
public class ConstantConfiguration {
    public final NotificationTemplate NOTIFICATION_TEMPLATE = new NotificationTemplate();

    public class NotificationTemplate {
        public final String VERIFICATION_TICKET_HEADER = "Hủy đơn hàng";
        public final String VERIFICATION_TICKET_SUBHEADER = "";
        public final String VERIFICATION_TICKET_CONTENT = "Bạn có một đơn hàng bị hủy! Vui lòng kiểm tra lại!";
    }
}
