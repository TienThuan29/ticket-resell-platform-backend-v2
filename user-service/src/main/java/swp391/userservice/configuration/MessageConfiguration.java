package swp391.userservice.configuration;

import org.springframework.stereotype.Component;

/**
 * Author: Nguyen Tien Thuan
 */
@Component("userServiceMessageConfiguration")
public class MessageConfiguration {

    // Error message
    public final String ERROR_ACCOUNT_FORBIDDEN = "Tài khoản này đã bị cấm!";
    public final String ERROR_USERNAME_EXIST = "Tên đăng nhập đã tồn tại!";
    public final String ERROR_REGISTER_FAIL = "Đăng ký không thành công!";
    public final String ERROR_REGISTER_SUCCESS = "Đăng ký thành công!";
    public final String ERROR_NOT_FOUND_USERID = "Không thể tìm thấy người dùng";
    public final String ERROR_INVALID_USERNAME_PASSWORD = "Tên đăng nhập hoặc mật khẩu không đúng";
    public final String ERROR_UPDATE_AVATAR_FAIL = "Không thể cập nhật ảnh";
    public final String ERROR_UPDATE_ISSELLER_FAIL = "Có gì đó không ổn! Vui lòng thử lại!";
    public final String ERROR_OTP_INVALID = "Mã OTP không hợp lệ";
    public final String ERROR_OTP_ISEXPIRED = "Mã OTP hết thời gian hiệu lực";
    public final String ERROR_EXIST_EMAIL = "Email này đã được đăng ký!";
    public final String ERROR_INVALID_EMAIL = "Email này không tồn tại!";
    public final String ERROR_USERNAME_NOTFOUND = "Không tìm thấy tên đăng nhập!";
    public final String ERROR_LOGIN_SESSION_EXPIRED = "Phiên đăng nhập đã hết hạn! Vui lòng đăng nhập lại!";
    public final String ERROR_INVALID_TOKEN = "Token không hợp lệ!";
    public final String ERROR_OLD_PASSWORD_NOT_CORRECT = "Mật khẩu cũ không đúng!";
    public final String ERROR_EMAIL_NOT_REGISTER = "Email này chưa dđực đăng ký!";

    // Success message
    public final String MESSAGE_UPDATE_USER_SUCCESS = "Cập nhật thông tin thành công";
    public final String MESSAGE_UPDATE_AVATAR_SUCCESS = " Cập nhật ảnh đại diện thành công";
    public final String MESSAGE_UPDATE_ISSELLER_SUCCESS = "Bạn đã đồng ý với điều khoản";
    public final String MESSAGE_UPDATE_NEW_PASSWORD_SUCCESS = "Đổi mật khẩu thành công";
    public final String MESSAGE_RESET_PASSWORD_SUCCSESS = "Cập nhật mật khẩu thành công";

}
