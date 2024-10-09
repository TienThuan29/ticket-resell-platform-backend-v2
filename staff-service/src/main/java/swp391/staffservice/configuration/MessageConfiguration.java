package swp391.staffservice.configuration;

import org.springframework.stereotype.Component;

@Component("staffServiceMessageConfiguration")
public class MessageConfiguration {

    public final String ERROR_NOT_FOUND_TICKET = "Không thể tìm thấy vé";
    public final String ERROR_WHILE_VERIFY_TICKET = "Có sự cố khi kiểm duyệt vé! Hãy thử lại!";

    // Success
    public final String MESSAGE_VERIFY_TICKET_SUCCESS = "Duyệt vé thành công!";
}
