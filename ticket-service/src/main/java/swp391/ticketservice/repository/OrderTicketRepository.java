package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.OrderTicket;
import swp391.entity.embedable.OrderTicketID;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderTicketRepository extends JpaRepository<OrderTicket, OrderTicketID> {

    @Query(
            "SELECT ot FROM OrderTicket ot" +
            " WHERE ot.buyer.id =:userId AND ot.isAccepted = false AND (ot.note IS NULL OR ot.note = '')"
    )
    List<OrderTicket> getProcessingOrderTicket(Long userId);

    @Query(
            "SELECT ot FROM OrderTicket ot " +
            "WHERE ot.genericTicket.id IN " +
            "( SELECT gt.id FROM GenericTicket gt WHERE gt.seller.id =:sellerId) " +
            "AND ot.isAccepted = false "
    )
    List<OrderTicket> getAllRequestOrderTicket(Long sellerId);

    @Query("SELECT ot FROM OrderTicket ot WHERE ot.orderTicketID.orderNo =:orderNo")
    Optional<OrderTicket> findByOrderNo(String orderNo);
}
