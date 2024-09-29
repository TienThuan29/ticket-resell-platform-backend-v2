package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Category;
import swp391.entity.Event;
import swp391.entity.GenericTicket;
import swp391.entity.User;

import java.util.List;

@Repository
public interface GenericTicketRepository extends JpaRepository<GenericTicket, Long> {
    List<GenericTicket> findByCategory(Category category);

    List<GenericTicket> findByEvent(Event event);

    List<GenericTicket> findBySeller(User seller);

    @Query(
            "SELECT COUNT(*) FROM Ticket t " +
            "INNER JOIN GenericTicket gt ON t.genericTicket.id = gt.id " +
            "WHERE gt.id =:genericTicketId AND t.isBought = false " +
            "GROUP BY gt.id"
    )
    Integer getTotalTicketsInGenericTicket(Long genericTicketId);

}
