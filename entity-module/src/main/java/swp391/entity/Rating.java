package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import swp391.entity.embedable.RatingID;

@Entity
@Table(name = "rating")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @EmbeddedId
    private RatingID ratingID;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("buyerId")
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("genericTicketId")
    private GenericTicket genericTicket;

    @Column(name = "comment", columnDefinition = "nvarchar(MAX)")
    private String comment;

    @Column(name = "stars", nullable = false)
    private Short stars;
}
