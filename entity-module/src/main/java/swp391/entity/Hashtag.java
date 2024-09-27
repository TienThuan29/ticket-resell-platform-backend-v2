package swp391.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hashtags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Hashtag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "nvarchar(128)", unique = true, nullable = false)
    private String name;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

    @ManyToMany
    @JoinTable(
            name = "hashtag_event",
            joinColumns = {@JoinColumn(name = "hashtag_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> eventsList;
}
