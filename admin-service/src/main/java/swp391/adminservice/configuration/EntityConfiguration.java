package swp391.adminservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.*;

@Configuration("adminServiceConfiguration")
@Import({
        Policy.class,
        Staff.class,
        Transaction.class,
        Ticket.class,
        User.class,
        Event.class,
        GenericTicket.class
})
public class EntityConfiguration {
}
