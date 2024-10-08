package swp391.staffservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import swp391.entity.GenericTicket;
import swp391.entity.ReportFraud;
import swp391.entity.Staff;
import swp391.entity.Ticket;

@EntityScan(basePackageClasses = {
        Staff.class,
        Ticket.class,
        ReportFraud.class,
        GenericTicket.class
})
@SpringBootApplication
public class StaffServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffServiceApplication.class, args);
    }

}
