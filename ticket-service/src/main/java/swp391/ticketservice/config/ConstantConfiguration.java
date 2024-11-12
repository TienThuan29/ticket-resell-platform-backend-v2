package swp391.ticketservice.config;

import org.springframework.stereotype.Component;

@Component("ticketServiceConstantConfiguration")
public class ConstantConfiguration {
    public final NotificationTemplate NOTIFICATION_TEMPLATE = new NotificationTemplate();

    public class NotificationTemplate {
        public final String VERIFICATION_TICKET_HEADER = "Hủy đơn hàng";
        public final String VERIFICATION_TICKET_SUBHEADER = "";
        public final String VERIFICATION_TICKET_CONTENT = "Bạn có một đơn hàng bị hủy! Vui lòng kiểm tra lại!";

        public final String ORDER_TICKET_HEADER = "Đơn hàng mới";
        public final String ORDER_TICKET_SUBHEADER = "";
        public final String ORDER_TICKET_CONTENT = "Bạn có một đơn hàng mới! Vui lòng kiểm tra trong cửa hàng!";

        public final String ACCEPT_TO_SELL_TICKET_HEADER = "Đơn hàng đã được xác nhận";
        public final String ACCEPT_TO_SELL_SUBHEADER = "";
        public final String ACCEPT_TO_SELL_CONTENT = "Đơn hàng của bạn đã được xác nhận thành công! Vui lòng kiểm tra lại!";

        public final String REJECT_TO_SELL_HEADER = "Đơn hàng bị từ chối";
        public final String REJECT_TO_SELL_SUBHEADER = "";
        public final String REJECT_TO_SELL_CONTENT = "Bạn có đơn hàng bị từ chối! Vui lòng kiểm tra lại";
    }
}
