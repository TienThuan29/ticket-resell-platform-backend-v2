package swp391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import swp391.entity.fixed.GeneralProcess;
import java.util.Date;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticket_serial", length = 128, nullable = false)
    private String ticketSerial;

    @Lob
    @Column(name = "image", columnDefinition = "varbinary(MAX)")
    private byte[] image;

    @Column(name = "is_checked", columnDefinition = "bit default 0")
    private boolean isChecked;

    @Column(name = "is_bought", columnDefinition = "bit default 0")
    private boolean isBought;

    @Column(name = "is_valid", columnDefinition = "bit default 0")
    private boolean isValid;

    @Column(name = "note", columnDefinition = "nvarchar(MAX)")
    private String note;

    @Column(name = "process", columnDefinition = "varchar(32)")
    @Enumerated(value = EnumType.STRING)
    private GeneralProcess process = GeneralProcess.WAITING;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "bought_date")
    private Date boughtDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generic_ticket_id")
    private GenericTicket genericTicket;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff verifyStaff;
}
