package swp391.paymentsservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.Transaction;
import swp391.entity.User;

@Import({
        User.class,
        Transaction.class
})
@Configuration("paymentsServiceEntityConfiguration")
public class EntityConfiuration {
}
