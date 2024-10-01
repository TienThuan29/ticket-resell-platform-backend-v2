package swp391.emailservice.service;

import org.springframework.stereotype.Service;
import swp391.emailservice.dto.request.VerificationRequest;
import swp391.emailservice.dto.response.ApiResponse;

@Service
public interface IEmailService {

    ApiResponse<?> sendOTP(VerificationRequest request, String email);
}
