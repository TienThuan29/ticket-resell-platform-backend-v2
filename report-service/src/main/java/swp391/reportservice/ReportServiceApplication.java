package swp391.reportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import swp391.entity.*;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        GenericTicket.class,
        Ticket.class,
        User.class,
        Staff.class,
        ReportFraud.class,
        ReportType.class
})
public class ReportServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportServiceApplication.class, args);
    }

}
