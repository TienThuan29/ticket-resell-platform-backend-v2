package swp391.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import swp391.entity.*;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        Category.class,
        Event.class,
        GenericTicket.class,
        Ticket.class,
        Policy.class,
        User.class,
        Staff.class,
        OrderTicket.class,
        PaymentMethod.class,
        Rating.class,
        Transaction.class
})
@EnableFeignClients
public class TicketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class, args);
    }
}
