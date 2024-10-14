package swp391.paymentsservice.config;

import org.springframework.stereotype.Component;

/**
 * Author: Nguyen Nhat Truong
 */
@Component("paymentsServiceMessageConfig")
public class MessageConfiguration {

    //Success
    public final String SUCCESS_TRANSACTION= "Giao dịch thành công";

    //Failed
    public final String ERROR_USER = "Người dùng không tồn tại";
}
