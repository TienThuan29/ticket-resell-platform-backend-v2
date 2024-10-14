package swp391.entity.fixed;

public enum GeneralProcess {
    WAITING("Chờ xác nhận"),
    PROCESSING("Đang xử lý"),
    SELLING("Đang bán"),
    SUCCESS("Thành công"),
    REJECTED("Bị hủy"),
    CANCEL("Đã hủy"),
    FAIL("Thất bại"),
    REPORT_PROCESSING("Dang thực thi báo cáo")

    ;

    public final String content;

    GeneralProcess(String content) {
        this.content = content;
    }

}
