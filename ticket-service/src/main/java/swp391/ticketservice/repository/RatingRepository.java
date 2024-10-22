package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Rating;
import swp391.ticketservice.dto.response.RatingResponse;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("SELECT AVG(rt.stars) FROM Rating AS rt LEFT JOIN GenericTicket AS g " +
            "ON rt.genericTicket.id = g.id "+
            "WHERE g.seller.id = :sellerId")
    Float getAvgStars(Long sellerId);

    @Query("SELECT rt FROM Rating AS rt LEFT JOIN GenericTicket AS g " +
            "ON rt.genericTicket.id = g.id "+
            "WHERE g.seller.id = :sellerId")
    List<Rating> getListRating(Long sellerId);

    Optional<Rating> findByBuyerIdAndGenericTicketId(Long buyer_id, Long genericTicket_id);

    List<Rating> findByBuyerId(Long buyer_id);
}
