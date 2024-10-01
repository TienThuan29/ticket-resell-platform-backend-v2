package swp391.entity.fixed;

public enum VerificationType {
    VERIFY_EMAIL("Xác thực email đăng kí"),
    RESET_PASSWORD("Quên mật khẩu"),
    ;

    public final String content;

    VerificationType(String content) {
        this.content = content;
    }
}
