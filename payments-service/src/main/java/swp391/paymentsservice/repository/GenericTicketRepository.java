package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.entity.GenericTicket;

public interface GenericTicketRepository extends JpaRepository<GenericTicket, Long> {
}
