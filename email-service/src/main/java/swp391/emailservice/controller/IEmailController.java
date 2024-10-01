package swp391.emailservice.controller;

import swp391.emailservice.dto.request.VerificationRequest;
import swp391.emailservice.dto.response.ApiResponse;

public interface IEmailController {

    ApiResponse<?> sendOTP(VerificationRequest request, String email);
}
