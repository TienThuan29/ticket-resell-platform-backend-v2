package swp391.entity.embedable;

import jakarta.persistence.*;
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
public class RatingID implements Serializable {

    @Column(name = "generic_ticket_id",nullable = false)
    private Long genericTicketId;

    @Column(name = "user_id", nullable = false)
    private Long buyerId;

}
