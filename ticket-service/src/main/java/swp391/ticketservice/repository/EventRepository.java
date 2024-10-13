package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.entity.Event;
import swp391.entity.Hashtag;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.isDeleted = false")
    List<Event> getHappeningEvents();

    @Query("SELECT DISTINCT e " +
            "FROM Event e " +
            "INNER JOIN GenericTicket gt ON e.id = gt.event.id " +
            "INNER JOIN Category ct ON gt.category.id = ct.id " +
            "WHERE ct.name LIKE ?1 AND e.isDeleted = false")
    List<Event> getEventsByCategory(String categoryName);

    @Query("SELECT DISTINCT e " +
            "FROM Event e " +
            "WHERE e.id not in"+
            "(select id from GenericTicket )"
    )
    List<Event> getOtherEvent();

    @Query("SELECT e FROM Event e LEFT JOIN e.hashtags h WHERE " +
            "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:startDate IS NULL OR e.startDate = :startDate) AND " +
            "(:hashtags IS NULL OR h.id IN :hashtags) AND " +
            "e.isDeleted = false")
    List<Event> getEventByFilter(String name, Date startDate, List<Integer> hashtags);

//    @Query(
//            "SELECT e FROM Event e " +
//            "INNER JOIN "
//    )
//    List<Event> getEventByHashtagName(String hastagName);
}
