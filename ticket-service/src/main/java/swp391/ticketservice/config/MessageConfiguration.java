package swp391.ticketservice.config;

import org.springframework.stereotype.Component;

/**
 * Author: Nguyen Nhat Truong
 */
@Component("ticketServiceMessageConfig")
public class MessageConfiguration {
    public final String ERROR_NOT_ENOUGH_TICKET_TO_BUY = "Bạn không đủ số lượng vé để bán! Vui lòng kiểm tra lại!";
    public  final String ERROR_CANNOT_DUPLICATE_CANCEL_ORDER = "Đơn hàng đã được hủy! Vui lòng kiểm tra lại!";
    public final String INVALID_GENERICTICKET = "Vé tổng quan không hợp lệ";
    public final String INVALID_POLICY = "Chính sách không hợp lệ";
    public final String INVALID_CATEGORY = "Danh mục không hợp lệ";
    public final String INVALID_SELLER = "Người bán không hợp lệ";
    public final String INVALID_EVENT = "Sự kiện không hợp lệ";
    public final String SUCCESS_OPERATION = "Hành động thành công";
    public final String INVALID_TICKET = "Vé không hợp lệ";
    public final String INVALID_STAFF = "Nhân viên không hợp lệ";
    public final String INVALID_PROCESS = "Quá trình không hợp lệ";
    public final String INVALID_BUYER = "Người mua không hợp lệ";
    public final String SUCCESS_ADD_EVENT = "Thêm sự kiện thành công";
    public final String INVALID_GENERIC_TICKET_ID = "Có sự cố khi cập nhật thông tin vé! Vui lòng tạo lại vé!";
    public final String UPDATE_GENERIC_SUCCESS = "Cập nhật vé thành công!";
    public final String SUCCESS_ADD_HASHTAG = "Thêm hashtag thành công!";
    public final String FAIL_TO_DELETE_HASHTAG = "Xóa #hashtag không thành công! Vui lòng thử lại!";
    public final String SUCCESS_DELETE_HASHTAG = "Xóa #hashtag thành công!";
    public final String INVALID_HASHTAG_ID = "HashtagId không hợp lệ!";
    public final String SUCCESS_UPDATE_HASHTAG = "Cập nhật #hashtag thành công!";
    public final String INVALID_PAYMENT_METHOD_ID = "Phương thức thanh toán không hợp lệ!";
    public final String FAIL_ORDER_TICKET = "Đặt mua vé không thành công!";
    public final String SUCCESS_ORDER_TICKET = "Đặt mua vé thành công. Vui lòng chờ xác nhận từ người bán!";
    public final String ERROR_ACCEPT_TO_SELL_TICKET = "Có sự cố khi xác nhận bán! Vui lòng thử lại!";
    public final String SUCCESS_ACCEPT_TO_SELL_TICKET = "Xác nhận bán thành công!";
    public final String ERROR_ORDER_TICKET_NOT_FOUND = "Không tìm thấy đơn hàng!";
    public final String SUCCESS_DENY_TO_SELL_TICKET = "Từ chối bán vé thành công";
    public final String ERROR_DENY_TO_SELL_TICKET = "Có sự cố khi từ chối bán vé! Vui lòng thử lại!";
    public final String ERROR_CANCEL_TICKET_ORDER = "Có lỗi xảy ra khi hủy đơn hàng!";
    public final String SUCCESS_CANCEL_TICKET_ORDER = "Hủy đơn hàng thành công!";
    public final String SUCCESS_RATING = "Đánh giá thành công!";
    public final String ERROR_RATING = "Có sự cố xảy ra khi đánh giá!";
    public final String SUCCESS_MARK_IS_BOUGHT_TICKET = "Đánh dấu thành công!";
    public final String ERROR_MARK_IS_BOUGHT_TICKET = "Có sự cố khi chỉnh sửa! Vui lòng thử lại";
    public final String SUCCESS_MARK_DELIVERED_PAPER_TICKET = "Xác nhận đã giao thành công!";
    public final String ERROR_MARK_DELIVERED_PAPER_TICKET = "Có sự cố khi xác nhận! Vui lòng thử lại!";
    public final String ERROR_DELETE_TICKET_FROM_SHOP = "Có sự cố khi xóa vé ra khỏi cửa hàng! Vui lòng thử lại!";
    public final String SUCCESS_DELETE_TICKET_FROM_SHOP = "Xóa vé khỏi cửa hàng thành công!";
    public final String ERROR_NOT_ENOUGH_MONEY = "Bạn không đủ số dư để thanh toán!";
    public final String SUCCESS_UPDATE_EVENT = "Cập nhật sự kiện thành công";
    public final String ERROR_UPDATE_EVENT = "Cập nhật sự kiên thất bại";
}

//public class MessageConfiguration {
//    public final String INVALID_GENERICTICKET;
//    public final String INVALID_POLICY;
//    public final String INVALID_CATEGORY;
//    public final String INVALID_SELLER;
//    public final String INVALID_EVENT;
//    public final String SUCCESS_OPERATION;
//    public final String INVALID_TICKET;
//    public final String INVALID_STAFF;
//    public final String INVALID_PROCESS;
//    public final String INVALID_BUYER;
//    public final String SUCCESS_ADD_EVENT;
//    public final String INVALID_GENERIC_TICKET_ID;
//    public final String UPDATE_GENERIC_SUCCESS;
//    public final String SUCCESS_ADD_HASHTAG;
//    public final String FAIL_TO_DELETE_HASHTAG;
//    public final String SUCCESS_DELETE_HASHTAG;
//    public final String INVALID_HASHTAG_ID;
//    public final String SUCCESS_UPDATE_HASHTAG;
//    public final String INVALID_PAYMENT_METHOD_ID;
//    public final String FAIL_ORDER_TICKET;
//    public final String SUCCESS_ORDER_TICKET;
//    public final String ERROR_ACCEPT_TO_SELL_TICKET;
//    public final String SUCCESS_ACCEPT_TO_SELL_TICKET;
//    public final String ERROR_ORDER_TICKET_NOT_FOUND;
//    public final String SUCCESS_DENY_TO_SELL_TICKET;
//    public final String ERROR_DENY_TO_SELL_TICKET;
//    public final String ERROR_CANCEL_TICKET_ORDER;
//    public final String SUCCESS_CANCEL_TICKET_ORDER;
//    public final String SUCCESS_RATING;
//    public final String ERROR_RATING;
//    public final String SUCCESS_MARK_IS_BOUGHT_TICKET;
//    public final String ERROR_MARK_IS_BOUGHT_TICKET;
//    public final String SUCCESS_MARK_DELIVERED_PAPER_TICKET;
//    public final String ERROR_MARK_DELIVERED_PAPER_TICKET;
//    public final String ERROR_DELETE_TICKET_FROM_SHOP;
//    public final String SUCCESS_DELETE_TICKET_FROM_SHOP;
//    public final String ERROR_NOT_ENOUGH_MONEY;
//
//    public MessageConfiguration() {
//        this.ERROR_NOT_ENOUGH_MONEY = "Bạn không đủ số dư để thanh toán!";
//        this.ERROR_DELETE_TICKET_FROM_SHOP = "Có sự cố khi xóa vé ra khỏi cửa hàng! Vui lòng thử lại!";
//        this.SUCCESS_DELETE_TICKET_FROM_SHOP = "Xóa vé khỏi cửa hàng thành công!";
//        this.INVALID_GENERICTICKET = "Vé tổng quan không hợp lệ";
//        this.INVALID_POLICY = "Chính sách không hợp lệ";
//        this.INVALID_CATEGORY = "Danh mục không hợp lệ";
//        this.INVALID_SELLER = "Người bán không hợp lệ";
//        this.INVALID_EVENT = "Sự kiện không hợp lệ";
//        this.SUCCESS_OPERATION = "Hành động thành công";
//        this.INVALID_TICKET = "Vé không hợp lệ";
//        this.INVALID_STAFF = "Nhân viên không hợp lệ";
//        this.INVALID_PROCESS = "Quá trình không hợp lệ";
//        this.INVALID_BUYER= "Người mua không hợp lệ";
//        this.SUCCESS_ADD_EVENT = "Thêm sự kiện thành công";
//        this.INVALID_GENERIC_TICKET_ID = "Có sự cố khi cập nhật thông tin vé! Vui lòng tạo lại vé!";
//        this.UPDATE_GENERIC_SUCCESS = "Cập nhật vé thành công!";
//        this.SUCCESS_ADD_HASHTAG = "Thêm hashtag thành công!";
//        this.FAIL_TO_DELETE_HASHTAG = "Xóa #hashtag không thành công! Vui lòng thử lại!";
//        this.SUCCESS_DELETE_HASHTAG = "Xóa #hashtag thành công!";
//        this.INVALID_HASHTAG_ID = "HashtagId không hợp lệ!";
//        this.SUCCESS_UPDATE_HASHTAG = "Cập nhật #hashtag thành công!";
//        this.INVALID_PAYMENT_METHOD_ID = "Phương thức thanh toán không hợp lệ!";
//        this.FAIL_ORDER_TICKET = "Đặt mua vé không thành công!";
//        this.SUCCESS_ORDER_TICKET = "Đặt mua vé thành công. Vui lòng chờ xác nhận từ người bán!";
//        this.ERROR_ACCEPT_TO_SELL_TICKET = "Có sự cố khi xác nhận bán! Vui lòng thử lại!";
//        this.SUCCESS_ACCEPT_TO_SELL_TICKET = "Xác nhận bán thành công!";
//        this.SUCCESS_DENY_TO_SELL_TICKET = "Từ chối bán vé thành công";
//        this.ERROR_DENY_TO_SELL_TICKET = "Có sự cố khi từ chối bán vé! Vui lòng thử lại!";
//        this.ERROR_ORDER_TICKET_NOT_FOUND = "Không tìm thấy đơn hàng!";
//        this.ERROR_CANCEL_TICKET_ORDER = "Có lỗi xảy ra khi hủy đơn hàng!";
//        this.SUCCESS_CANCEL_TICKET_ORDER = "Hủy đơn hàng thành công!";
//        this.SUCCESS_RATING = "Đánh giá thành công!";
//        this.ERROR_RATING = "Có sự cố xảy ra khi đánh giá!";
//        this.SUCCESS_MARK_IS_BOUGHT_TICKET = "Đánh dấu thành công!";
//        this.ERROR_MARK_IS_BOUGHT_TICKET = "Có sự cố khi chỉnh sửa! Vui lòng thử lại";
//        this.SUCCESS_MARK_DELIVERED_PAPER_TICKET = "Xác nhận đã giao thành công!";
//        this.ERROR_MARK_DELIVERED_PAPER_TICKET = "Có sụ cố khi xác nhận! Vui lòng thử lại!";
//    }
//}
