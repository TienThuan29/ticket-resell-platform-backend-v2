package swp391.emailservice.config;

import org.springframework.stereotype.Component;

@Component
public class Template {

    public final String sendOTPVerifyEmail(String otp){
        return "<html>" +
                "<body>" +
                "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>" +
                "<h1 style='color: #333;'>Mã OTP của bạn</h1>" +
                "<p style='font-size: 16px;'>Xin chào,</p>" +
                "<p style='font-size: 16px;'>Vui lòng sử dụng mã OTP sau để xác minh địa chỉ email của bạn:</p>" +
                "<h2 style='color: #e74c3c; font-size: 24px;'>" + otp + "</h2>" +
                "<p style='font-size: 16px;'>Mã OTP này có hiệu lực trong vòng 5 phút.</p>" +
                "<p style='font-size: 16px;'>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.</p>" +
                "<br>" +
                "<p style='font-size: 14px; color: #999;'>Trân trọng,<br>Ticket Resell SWP391</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }


    public final String sendOTPResetPassword(String otp){
        return "<html>" +
                "<body>" +
                "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>" +
                "<h2 style='color: #333;'>Đặt lại mật khẩu của bạn</h2>" +
                "<p style='font-size: 16px;'>Xin chào,</p>" +
                "<p style='font-size: 16px;'>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn. Vui lòng sử dụng Mã OTP sau để đặt lại mật khẩu:</p>" +
                "<h2 style='color: #e74c3c; font-size: 24px;'>" + otp + "</h2>" +
                "<p style='font-size: 16px;'>Mã OTP này có hiệu lực trong vòng 5 phút.</p>" +
                "<p style='font-size: 16px;'>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này. Không có thay đổi nào sẽ được thực hiện đối với tài khoản của bạn.</p>" +
                "<br>" +
                "<p style='font-size: 14px; color: #999;'>Trân trọng,<br>Ticket Resell SWP391</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
