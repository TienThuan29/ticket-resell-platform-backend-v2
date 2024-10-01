package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import swp391.entity.fixed.VerificationType;

@Entity
@Table(name = "verification_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationUser {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "expiration_time")
    private Long expirationTime;

    @Column(name = "verifiaction_code", length = 10)
    private String verificationCode;

    @Column(name = "verificationType", columnDefinition = "varchar(32)")
    @Enumerated(value = EnumType.STRING)
    private VerificationType verificationType;
}
