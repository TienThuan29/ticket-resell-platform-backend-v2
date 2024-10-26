package swp391.paymentsservice.config;

import org.springframework.stereotype.Component;

/**
 * Author: Nguyen Nhat Truong
 */
@Component("paymentsServiceMessageConfig")
public class MessageConfiguration {

    //Success
    public final String SUCCESS_TRANSACTION= "Giao dịch thành công";
    public final String SUCCESS_WITHDRAWAL_AMOUNT = "Rút tiền thành công!";

    //Failed
    public final String ERROR_USER = "Người dùng không tồn tại";
    public final String ERROR_REPORTED_USER= "Người bị báo cáo hợp không hợp lệ";
    public final String ERROR_TYPE_POLICY = "Thể loại chính sách không hợp lệ";
    public final String ERROR_POLICY = "Không tìm thấy chính sách";
    public final String ERROR_WITHDRAWAL_AMOUNT = "Số tiền rút không hợp lệ! Vui lòng nhập lại!";
    public final String ERROR_USER_BALANCE_NOT_ENOUGH = "Bạn không đủ tiền trong tài để rút!";
    public final String ERROR_WITHDRAWAL_AT_SERVER = "Có sự cố xảy ra khi rút tiền!";
}
