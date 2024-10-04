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

    @Query(
            "SELECT gt FROM GenericTicket gt " +
            "WHERE gt.id IN " +
            "( " +
                    "SELECT t.genericTicket.id FROM Ticket t " +
                    "WHERE t.isValid = true AND t.isChecked = true AND t.isBought = false " +
                    "GROUP BY t.genericTicket.id HAVING COUNT(t.ticketSerial) > 0" +
            ") " +
            "AND gt.event.id =:eventId"
    )
    List<GenericTicket> findAllValidGenericTicket(Integer eventId);

    List<GenericTicket> findBySeller(User seller);

    @Query(
            "SELECT COUNT(*) FROM Ticket t " +
            "INNER JOIN GenericTicket gt ON t.genericTicket.id = gt.id " +
            "WHERE gt.id =:genericTicketId AND t.isBought = false " +
            "GROUP BY gt.id"
    )
    Integer getTotalTicketsInGenericTicket(Long genericTicketId);

    @Query("SELECT g FROM GenericTicket g WHERE " +
            "(:minPrice IS NULL OR g.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR g.price <= :maxPrice) AND " +
            "(:ticketName IS NULL OR g.ticketName LIKE %:ticketName%) AND " +
            "(:area IS NULL OR g.area = :area) AND " +
            "(:isPaper IS NULL OR g.isPaper = :isPaper)")
    List<GenericTicket> findByFilters(
            Long minPrice,
            Long maxPrice,
            String ticketName,
            String area,
            Boolean isPaper
    );
}
