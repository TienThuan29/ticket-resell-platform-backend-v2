package swp391.emailservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import swp391.emailservice.dto.request.VerificationRequest;
import swp391.emailservice.dto.response.ApiResponse;
import swp391.emailservice.service.IEmailService;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController implements IEmailController{

    private final IEmailService emailService;

    @Override
    @PostMapping("/send-otp")
    public ApiResponse<?> sendOTP(@RequestBody VerificationRequest request, @RequestParam String email) {
        return emailService.sendOTP(request, email);
    }
}
