package swp391.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.User;
import swp391.entity.VerificationUser;
import swp391.entity.fixed.VerificationType;

@Import({
        User.class,
        VerificationUser.class
})
@Configuration("userServiceEntityConfiguration")
public class EntityConfiguration {}
