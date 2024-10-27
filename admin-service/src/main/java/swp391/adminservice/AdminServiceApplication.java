package swp391.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import swp391.entity.*;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        Policy.class,
        Staff.class,
        Transaction.class,
        Ticket.class,
        User.class,
        Event.class
})
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
