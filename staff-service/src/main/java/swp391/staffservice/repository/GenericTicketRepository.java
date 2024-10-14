package swp391.staffservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.GenericTicket;
import java.util.List;

@Repository
public interface GenericTicketRepository extends JpaRepository<GenericTicket, Long> {

    @Query("SELECT gt FROM GenericTicket gt WHERE gt.id IN " +
            "( SELECT t.genericTicket.id FROM Ticket t  WHERE t.verifyStaff.id =:staffId)"
    )
    List<GenericTicket> getAllGenericTicketOfStaff(Long staffId);

}
