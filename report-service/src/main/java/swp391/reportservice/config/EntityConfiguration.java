package swp391.reportservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.*;

@Import({
        GenericTicket.class,
        Ticket.class,
        User.class,
        Staff.class,
        ReportFraud.class,
        ReportType.class
})
@Configuration("reportServiceEntityConfiguration")
public class EntityConfiguration {
}
