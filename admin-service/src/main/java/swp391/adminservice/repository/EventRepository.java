package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.adminservice.dto.response.EventRevenueResponse;
import swp391.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "EXEC SelectEventRevenue", nativeQuery = true)
    List<EventRevenueResponse> getEventRevenue();
}
