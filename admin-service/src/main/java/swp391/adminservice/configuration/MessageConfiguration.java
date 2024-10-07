package swp391.adminservice.configuration;

import org.springframework.stereotype.Component;

@Component("adminServiceMessageConfiguration")
public class MessageConfiguration {
    // Error
    public final String ERROR_STAFF_NOT_FOUND = "Không thể tìm thấy nhân viên!";
    public final String ERROR_EXIST_USERNAME = "Tên đăng nhập đã tồn tại!";
    public final String ERROR_EXIST_EMAIL = "Email này đã được đăng ký!";
    public final String ERROR_INVALID_TOKEN = "Token không hợp lệ!";
    public final String ERROR_USERNAME_NOTFOUND = "Tên đăng nhập không tồn tại";

    // Success
    public final String SUCCESS_REGISTER_STAFF = "Đăng ký nhân viên thành công!";
    public final String ERROR_INVALID_USERNAME_PASSWORD = "Tên đăng nhập hoặc mật khẩu không đúng!";

}
