package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.GenericTicket;

@Repository
public interface GenericTicketRepository extends JpaRepository<GenericTicket, Long> {
}
