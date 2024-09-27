package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "nvarchar(128)", unique = true, nullable = false)
    private String name;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

    @OneToMany(mappedBy = "category")
    private Collection<GenericTicket> genericTickets;
}
