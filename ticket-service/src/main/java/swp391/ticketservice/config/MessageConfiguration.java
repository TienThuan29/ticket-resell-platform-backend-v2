package swp391.ticketservice.config;

import org.springframework.stereotype.Component;

/**
 * Author: Nguyen Nhat Truong
 */
@Component("ticketServiceMessageConfig")
public class MessageConfiguration {
    public final String INVALID_GENERICTICKET;
    public final String INVALID_POLICY;
    public final String INVALID_CATEGORY;
    public final String INVALID_SELLER;
    public final String INVALID_EVENT;
    public final String SUCCESS_OPERATION;
    public final String INVALID_TICKET;
    public final String INVALID_STAFF;
    public final String INVALID_PROCESS;
    public final String INVALID_BUYER;
    public final String SUCCESS_ADD_EVENT;

    public MessageConfiguration() {
        this.INVALID_GENERICTICKET = "Vé tổng quan không hợp lệ";
        this.INVALID_POLICY = "Chính sách không hợp lệ";
        this.INVALID_CATEGORY = "Danh mục không hợp lệ";
        this.INVALID_SELLER = "Người bán không hợp lệ";
        this.INVALID_EVENT = "Sự kiện không hợp lệ";
        this.SUCCESS_OPERATION = "Hành động thành công";
        this.INVALID_TICKET = "Vé không hợp lệ";
        this.INVALID_STAFF = "Nhân viên không hợp lệ";
        this.INVALID_PROCESS = "Quá trình không hợp lệ";
        this.INVALID_BUYER= "Người bán không hợp lệ";
        this.SUCCESS_ADD_EVENT = "Thêm sự kiện thành công";
    }
}
