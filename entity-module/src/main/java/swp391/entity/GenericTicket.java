package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "generic_tickets")
@NoArgsConstructor
@AllArgsConstructor
public class GenericTicket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_name", columnDefinition = "nvarchar(128)", nullable = false)
    private String ticketName;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "sale_percent")
    private Double salePercent;

    @Column(name = "area", columnDefinition = "nvarchar(128)")
    private String area;

    @Column(name = "expired_date_time", nullable = false)
    private Date expiredDateTime;

    @Column(name = "description", columnDefinition = "nvarchar(1024)")
    private String description;

    @Column(name = "link_event", columnDefinition = "nvarchar(512)")
    private String linkEvent;

    @Column(name = "is_paper")
    private boolean isPaper;

    @OneToMany(mappedBy = "genericTicket")
    private Collection<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "genericTicket", fetch = FetchType.LAZY)
    private List<Rating> ratingList;

}
