package swp391.entity.embedable;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingID implements Serializable {

    @Column(name = "generic_ticket_id",nullable = false)
    private Long genericTicketId;

    @Column(name = "user_id", nullable = false)
    private Long buyerId;

}
