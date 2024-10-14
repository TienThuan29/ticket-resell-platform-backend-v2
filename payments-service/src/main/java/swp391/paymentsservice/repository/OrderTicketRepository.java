package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.OrderTicket;
import swp391.entity.embedable.OrderTicketID;

@Repository
public interface OrderTicketRepository extends JpaRepository<OrderTicket, OrderTicketID> {

}
