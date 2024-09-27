package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    Optional<List<Ticket>> findByProcess(GeneralProcess process);
}
