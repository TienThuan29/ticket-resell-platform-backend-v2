package swp391.reportservice.config;

import org.springframework.stereotype.Component;

@Component("reportServiceMessageConfiguration")
public class MessageConfiguration {

    //Success
    public final String SUCCESS_OPERATION= "Hành động thực thi thành công";
    public final String SUCCESS_CREATE_REPORT="Báo cáo được tạo thành công";

    //Error
    public final String ERROR_ACCUSER= "Người báo hợp không hợp lệ";
    public final String ERROR_STAFF= "Nhân viên không hợp lệ";
}
