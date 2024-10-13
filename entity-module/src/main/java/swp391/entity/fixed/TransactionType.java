package swp391.entity.fixed;

public enum TransactionType {

    DEPOSIT("Nạp tiền"),
    WITHDRAWAL("Rút tiền"),
    SELLING("Bán vé"),
    BUYING("Mua vé"),
    DENIED_TO_SELL("Bị từ chối bán")

    ;

    public final String content;

    TransactionType(String content) {
        this.content = content;
    }
}
