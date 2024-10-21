package swp391.notificationservice.configuration;

import org.springframework.stereotype.Component;

@Component
public class MessageConfiguration {

    public final String SUCCESS_SEND_NOTIFICATION = "Gửi thông báo thành công!";
    public final String ERROR_SEND_NOTIFICATION = "Gửi thông báo không thành công!";
    public final String SUCCESS_MARK_READ_NOTIFICATION = "Đánh dấu đã đọc thành công!";
    public final String ERROR_MARK_READ_NOTIFICATION = "Có sự cố xảy ra! Vui lòng thử lại!";
    public final String SUCCESS_MARK_DELETED_NOTIFICATION = "Xóa thông báo thành công!";
    public final String ERROR_MARK_DELETED_NOTIFICATION = "Có sự cố xảy ra! Vui lòng thử lại!";
    public final String SUCCESS_DELETE_FOREVER_NOTIFICATION = "Xóa vĩnh viễn thông báo thành công";
    public final String ERROR_DELETE_FOREVER_NOTIFICATION = "Có sự cố xảy ra! Vui lòng thử lại!";

}
