package swp391.entity.embedable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketID implements Serializable {

    @Column(name = "ticket_serial", length = 128)
    private String ticketSerial;

    @Column(name = "generic_ticket_id", nullable = false)
    private Long genericTicketId;
}
