package swp391.adminservice.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.adminservice.dto.response.EventRevenueResponse;
import swp391.entity.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT new swp391.adminservice.dto.response.EventRevenueResponse(e.name, CAST(COUNT(t.id) AS int), e.startDate, " +
            "SUM(CASE WHEN t.isBought = true THEN gt.price ELSE 0 END)) " +
            "FROM Event e LEFT JOIN GenericTicket gt " +
            "ON e.id = gt.event.id LEFT JOIN Ticket t " +
            "ON gt.id = t.genericTicket.id " +
            "GROUP BY e.id, e.name, e.startDate")
    List<EventRevenueResponse> getEventRevenue();
}
