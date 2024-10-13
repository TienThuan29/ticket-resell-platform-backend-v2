package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.GenericTicket;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<List<Ticket>> findByProcess(GeneralProcess process);

    List<Ticket> findByGenericTicket(GenericTicket genericTicket);

    @Query(
            "SELECT t FROM Ticket t WHERE t.genericTicket.id =:genericTicketId"
    )
    List<Ticket> getNotBoughtTicketByGenericTicket(Long genericTicketId);

    @Query(
            "SELECT t FROM Ticket t WHERE t.genericTicket.id =:genericTicketId AND t.isBought = false"
    )
    List<Ticket> getNotBoughtTicketByGenericTicketNotBought(Long genericTicketId);

    @Query(
            "SELECT t FROM GenericTicket gt " +
            "INNER JOIN Ticket t on t.genericTicket.id = gt.id " +
            "WHERE t.isBought = true AND gt.seller.id =:sellerId"
    )
    List<Ticket> getAllBoughtTicketsBySeller(Long sellerId);

    @Query("SELECT t FROM Ticket t WHERE t.buyerId =:buyerId")
    List<Ticket> getAllBoughtTicketsByBuyer(Long buyerId);

}
