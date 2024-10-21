package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import swp391.entity.embedable.OrderTicketID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "order_tickets")
@NoArgsConstructor
@AllArgsConstructor
public class OrderTicket {

    @EmbeddedId
    private OrderTicketID orderTicketID;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("buyerId")
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("genericTicketId")
    private GenericTicket genericTicket;

    @Column(name = "is_accepted", columnDefinition = "bit default 0")
    private boolean isAccepted;

    @Column(name = "is_canceled", columnDefinition = "bit default 0")
    private boolean isCanceled;

    @Column(name = "note", columnDefinition = "nvarchar(1024)")
    private String note;

    @Column(name = "total_price")
    private Long totalPrice;
}
