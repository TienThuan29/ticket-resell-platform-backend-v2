package swp391.paymentsservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.*;
import swp391.entity.fixed.TransactionType;

@Import({
        User.class,
        Transaction.class,
        GenericTicket.class,
        Ticket.class,
        ReportFraud.class,
        OrderTicket.class
})
@Configuration("paymentsServiceEntityConfiguration")
public class EntityConfiguration {
}
