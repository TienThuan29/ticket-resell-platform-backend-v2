package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "nvarchar(256)", nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "image", columnDefinition = "varbinary(MAX)", nullable = false)
    private byte[] image;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "detail", columnDefinition = "nvarchar(1024)", nullable = false)
    private String detail;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "hashtag_event",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "hashtag_id")}
    )
    private Collection<Hashtag> hashtags;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Collection<GenericTicket> genericTickets;
}
