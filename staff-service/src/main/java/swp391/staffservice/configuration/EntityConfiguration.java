package swp391.staffservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.*;

@Import({
        Staff.class,
        Ticket.class,
        ReportFraud.class,
        GenericTicket.class
})
@Configuration("staffServiceEntityConfiguration")
public class EntityConfiguration {
}
