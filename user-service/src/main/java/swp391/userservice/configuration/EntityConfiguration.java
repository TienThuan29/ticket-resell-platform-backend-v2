package swp391.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swp391.entity.Token;
import swp391.entity.User;
import swp391.entity.VerificationUser;

@Import({
        User.class,
        VerificationUser.class,
        Token.class
})
@Configuration("userServiceEntityConfiguration")
public class EntityConfiguration {}
