package swp391.emailservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import swp391.emailservice.config.Template;
import swp391.emailservice.dto.request.VerificationRequest;
import swp391.emailservice.dto.response.ApiResponse;

@Component
@RequiredArgsConstructor
public class EmailService implements IEmailService{

    private final JavaMailSender javaMailSender;
    private final Template template;

    @Value("${spring.mail.username}")
    private String fromEmailID;

    private boolean sendEmail(String recipient, String body, String subject){
        boolean isSuccess= false;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            message.setFrom(new InternetAddress(fromEmailID));
            message.setRecipients(MimeMessage.RecipientType.TO, recipient);
            message.setSubject(subject);

            message.setContent(body, "text/html; charset=utf-8");

            javaMailSender.send(message);
            isSuccess= true;
        }
        catch (MessagingException exception){
            exception.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public ApiResponse<?> sendOTP(VerificationRequest request, String email) {
        boolean isSuccess=
                sendEmail(email,
                template.sendOTPVerifyEmail(request.getVerificationCode()),
                "Mã OTP để hoàn tất việc đăng kí"
                );

        return new ApiResponse<>(
                HttpStatus.OK,
                isSuccess?"Success":"Failed"
        );
    }
}
