package swp391.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import swp391.entity.User;
import swp391.entity.VerificationUser;
import swp391.entity.fixed.VerificationType;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        User.class,
        VerificationUser.class
})
@EnableFeignClients
@EnableScheduling
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
