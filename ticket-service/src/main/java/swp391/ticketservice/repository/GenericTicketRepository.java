package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Category;
import swp391.entity.Event;
import swp391.entity.GenericTicket;

import java.util.List;

@Repository
public interface GenericTicketRepository extends JpaRepository<GenericTicket, Long> {
    List<GenericTicket> findByCategory(Category category);

    List<GenericTicket> findByEvent(Event event);
}
