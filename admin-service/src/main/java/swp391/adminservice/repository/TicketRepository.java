package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("select count(t.id) from Ticket t where t.isBought = true")
    Integer countBoughtTickets();

    @Query("select count(t.id) from Ticket t left join GenericTicket g " +
            "on t.genericTicket.id = g.id " +
            "where t.process =:process and g.expiredDateTime > CURRENT_TIMESTAMP")
    Integer countSellingTickets(GeneralProcess process);
}
