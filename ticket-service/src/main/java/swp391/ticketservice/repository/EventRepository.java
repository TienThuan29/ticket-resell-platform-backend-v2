package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Event;
import swp391.ticketservice.dto.response.EventResponse;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.isDeleted = false")
    List<Event> getHappeningEvents();

}
