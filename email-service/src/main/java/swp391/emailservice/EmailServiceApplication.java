package swp391.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import swp391.entity.*;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        Category.class,
        Event.class,
        GenericTicket.class,
        Ticket.class,
        Policy.class,
        User.class,
        Staff.class,
        VerificationUser.class
})
public class EmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}
