package swp391.entity.fixed;

import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
public enum TransactionType {

    DEPOSIT("Nạp tiền"),
    WITHDRAWAL("Rút tiền"),
    SELLING("Bán vé"),
    BUYING("Mua vé"),
    DENIED_TO_SELL("Bị từ chối bán"),
    REPORTED("Bị báo cáo"),
    REFUND("Hoàn tiền vé đã báo cáo")
    ;

    public final String content;

    TransactionType(String content) {
        this.content = content;
    }

}
