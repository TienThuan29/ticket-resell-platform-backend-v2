package swp391.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "payment_method")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaymentMethod {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "nvarchar(128)", unique = true, nullable = false)
    private String name;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

}
