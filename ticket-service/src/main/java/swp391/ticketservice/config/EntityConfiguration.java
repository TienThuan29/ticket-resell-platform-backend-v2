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
<<<<<<< HEAD
        Rating.class
=======
        Transaction.class
>>>>>>> e020ee652e212f6555e91cafe6f5417ec437c4de
})
@Configuration("ticketServiceEntityConfiguration")
public class EntityConfiguration {
}
