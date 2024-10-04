package swp391.paymentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import swp391.entity.Transaction;
import swp391.entity.User;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        User.class,
        Transaction.class
})
public class PaymentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsServiceApplication.class, args);
    }

}
