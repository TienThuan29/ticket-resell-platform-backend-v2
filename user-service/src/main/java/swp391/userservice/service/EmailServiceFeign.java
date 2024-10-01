package swp391.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.entity.VerificationUser;

@FeignClient(name = "email-service", url = "http://localhost:8090")
public interface EmailServiceFeign {

    @PostMapping("/api/email/send-otp")
    void sendOtpEmail(@RequestBody VerificationUser verificationUser,@RequestParam String email);
}
