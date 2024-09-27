package swp391.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "policy")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Policy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String content;

    @Column(name = "fee", nullable = false)
    private Integer fee;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "type_policy_id")
    private TypePolicy typePolicy;

}
