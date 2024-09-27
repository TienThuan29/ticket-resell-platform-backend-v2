package swp391.adminservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.Policy;
import swp391.entity.Staff;

@Configuration("adminServiceConfiguration")
@Import({
        Policy.class,
        Staff.class
})
public class EntityConfiguration {
}
