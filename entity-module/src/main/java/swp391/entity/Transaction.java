package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import swp391.entity.fixed.TransactionType;
import java.util.Date;

@Entity
@Table(name = "[transaction]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trans_date", nullable = false)
    private Date transDate;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "is_done", nullable = false,columnDefinition = "bit default 0")
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "trans_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType type;
}
