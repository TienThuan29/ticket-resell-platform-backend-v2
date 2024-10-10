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

//    @Query(
//            "SELECT t FROM Ticket t " +
//            "INNER JOIN GenericTicket gt ON t.genericTicket.id = gt.id " +
//            "INNER JOIN User u ON u.id = gt.seller.id " +
//            "WHERE u.id =: userId"
//    )
//    List<Ticket> findByUserId(Long userId);
}
