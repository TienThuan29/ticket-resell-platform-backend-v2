package swp391.reportservice.config;

import org.springframework.stereotype.Component;

@Component("reportServiceConstantConfiguration")
public class ConstantConfiguration {
    public final NotificationTemplate NOTIFICATION_TEMPLATE = new NotificationTemplate();

    public class NotificationTemplate {

        public final String REPORT_FOR_ACCUSER_HEADER = "Phản hồi báo cáo vé";
        public final String REPORT_FOR_REPORTED_USER_HEADER = "Cảnh báo về việc gian lận bán vé";

    }
}
