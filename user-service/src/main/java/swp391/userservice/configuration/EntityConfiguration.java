package swp391.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.User;

@Import({
        User.class,
})
@Configuration("userServiceEntityConfiguration")
public class EntityConfiguration {}
