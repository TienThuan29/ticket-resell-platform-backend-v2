package swp391.emailservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.*;

@Import({
        GenericTicket.class,
        Ticket.class,
        User.class,
        VerificationUser.class
})
@Configuration("emailServiceEntityConfiguration")
public class EntityConfiguration {
}
