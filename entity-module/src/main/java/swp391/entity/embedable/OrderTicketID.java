package swp391.entity.embedable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTicketID implements Serializable {

    @Column(name = "generic_ticket_id", nullable = false)
    private Long genericTicketId;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

}
