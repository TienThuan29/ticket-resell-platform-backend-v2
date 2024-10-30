package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swp391.entity.GenericTicket;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("SELECT COUNT(t.id) FROM Ticket t INNER JOIN GenericTicket gt " +
            "ON t.genericTicket.id = gt.id " +
            "WHERE t.isBought = true AND t.buyerId IS NOT NULL " +
            "AND t.process = 'SUCCESS' AND gt IN :genericTicketList")
    Integer countBoughtTickets(List<GenericTicket> genericTicketList);


    @Query("select count(t.id) from Ticket t left join GenericTicket g " +
            "on t.genericTicket.id = g.id " +
            "where t.process =:process and g.expiredDateTime > CURRENT_TIMESTAMP")
    Integer countSellingTickets(GeneralProcess process);
}
