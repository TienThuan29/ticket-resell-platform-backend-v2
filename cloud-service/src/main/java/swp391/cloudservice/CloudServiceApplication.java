package swp391.cloudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceApplication.class, args);
    }

}
