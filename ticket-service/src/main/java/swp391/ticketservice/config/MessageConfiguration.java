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
    public final String INVALID_GENERIC_TICKET_ID;
    public final String UPDATE_GENERIC_SUCCESS;
    public final String SUCCESS_ADD_HASHTAG;
    public final String FAIL_TO_DELETE_HASHTAG;
    public final String SUCCESS_DELETE_HASHTAG;
    public final String INVALID_HASHTAG_ID;
    public final String SUCCESS_UPDATE_HASHTAG;
    public final String INVALID_PAYMENT_METHOD_ID;
    public final String FAIL_ORDER_TICKET;
    public final String SUCCESS_ORDER_TICKET;
    public final String ERROR_ACCEPT_TO_SELL_TICKET;
    public final String SUCCESS_ACCEPT_TO_SELL_TICKET;
    public final String ERROR_ORDER_TICKET_NOT_FOUND;
    public final String SUCCESS_DENY_TO_SELL_TICKET;
    public final String ERROR_DENY_TO_SELL_TICKET;

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
        this.INVALID_BUYER= "Người mua không hợp lệ";
        this.SUCCESS_ADD_EVENT = "Thêm sự kiện thành công";
        this.INVALID_GENERIC_TICKET_ID = "Có sự cố khi cập nhật thông tin vé! Vui lòng tạo lại vé!";
        this.UPDATE_GENERIC_SUCCESS = "Cập nhật vé thành công!";
        this.SUCCESS_ADD_HASHTAG = "Thêm #hashtag thành công!";
        this.FAIL_TO_DELETE_HASHTAG = "Xóa #hashtag không thành công! Vui lòng thử lại!";
        this.SUCCESS_DELETE_HASHTAG = "Xóa #hashtag thành công!";
        this.INVALID_HASHTAG_ID = "HashtagId không hợp lệ!";
        this.SUCCESS_UPDATE_HASHTAG = "Cập nhật #hashtag thành công!";
        this.INVALID_PAYMENT_METHOD_ID = "Phương thức thanh toán không hợp lệ!";
        this.FAIL_ORDER_TICKET = "Đặt mua vé không thành công!";
        this.SUCCESS_ORDER_TICKET = "Đặt mua vé thành công. Vui lòng chờ xác nhận từ người bán!";
        this.ERROR_ACCEPT_TO_SELL_TICKET = "Có sự cố khi xác nhận bán! Vui lòng thử lại!";
        this.SUCCESS_ACCEPT_TO_SELL_TICKET = "Xác nhận bán thành công!";
        this.ERROR_ORDER_TICKET_NOT_FOUND = "Không thể tìm thấy orderTicket";
        this.SUCCESS_DENY_TO_SELL_TICKET = "Từ chối bán vé thành công";
        this.ERROR_DENY_TO_SELL_TICKET = "Có sự cố khi từ chối bán vé! Vui lòng thử lại!";
    }
}
