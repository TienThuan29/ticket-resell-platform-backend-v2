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
    public final String ERROR_UPDATE_POLICY = "Có sự cố xảy ra khi cập nhật chính sách! Vui lòng thử lại!";
    public final String ERROR_INVALID_USERNAME_PASSWORD = "Tên đăng nhập hoặc mật khẩu không đúng!";
    public final String ERROR_DISABLE_ACCOUNT = "Có sự cố khi cấm tài khoản!";

    // Success
    public final String SUCCESS_REGISTER_STAFF = "Đăng ký nhân viên thành công!";
    public final String SUCCESS_UPDATE_POLICY = "Cập nhật chính sách thành công!";
    public final String SUCCESS_DISABLE_ACCOUNT = "Cấm tài khoản thành công!";
}
