package swp391.gatewayservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.User;

@Import({
        User.class
})
@Configuration("gatewayServiceConfiguration")
public class EntityConfiguration {}
