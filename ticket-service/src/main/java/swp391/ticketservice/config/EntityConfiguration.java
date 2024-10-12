package swp391.ticketservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.*;

@Import({
        Category.class,
        Event.class,
        GenericTicket.class,
        Ticket.class,
        Policy.class,
        User.class,
        Staff.class,
        OrderTicket.class,
        PaymentMethod.class,
        Transaction.class
})
@Configuration("ticketServiceEntityConfiguration")
public class EntityConfiguration {
}
