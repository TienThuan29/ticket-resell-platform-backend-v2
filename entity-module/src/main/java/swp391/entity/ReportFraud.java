package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import swp391.entity.fixed.GeneralProcess;

@Entity
@Table(name = "report_fraud")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReportFraud {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String content;

    @Lob
    @Column(name = "proof", columnDefinition = "varbinary(MAX)", nullable = false)
    private byte[] proof;

    @Column(name = "report_process", columnDefinition = "varchar(32)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GeneralProcess process;

    @ManyToOne
    @JoinColumn(name = "report_type_id")
    private ReportType reportType;

    @Column(name = "staff_id",nullable = false)
    private Long staffId;

    @Column(name = "reported_user_id", nullable = false)
    private Long reportedUserId;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "accuser_id")
    private User accuser;

}
