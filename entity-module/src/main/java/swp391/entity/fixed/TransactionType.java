package swp391.entity.fixed;

public enum TransactionType {

    DEPOSIT("Nạp tiền"),
    WITHDRAWAL("Rút tiền"),
    SELLING("Bán vé"),
    BUYING("Mua vé")


    ;

    public final String content;

    TransactionType(String content) {
        this.content = content;
    }
}
