package swp391.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_policy")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TypePolicy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "nvarchar(128)")
    private String name;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

}
