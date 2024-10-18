package swp391.paymentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import swp391.entity.*;
import swp391.entity.fixed.TransactionType;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        User.class,
        Transaction.class,
        GenericTicket.class,
        Ticket.class,
        ReportFraud.class,
        OrderTicket.class,
        Policy.class,
        Staff.class,
        TypePolicy.class
})
@EnableScheduling
public class PaymentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsServiceApplication.class, args);
    }

}
