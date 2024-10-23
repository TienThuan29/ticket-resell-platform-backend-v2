package swp391.adminservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.Policy;
import swp391.entity.Staff;
import swp391.entity.Transaction;

@Configuration("adminServiceConfiguration")
@Import({
        Policy.class,
        Staff.class,
        Transaction.class
})
public class EntityConfiguration {
}
