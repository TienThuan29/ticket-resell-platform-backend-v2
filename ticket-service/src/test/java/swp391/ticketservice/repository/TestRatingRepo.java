package swp391.ticketservice.Repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.ticketservice.repository.RatingRepository;
@DataJpaTest
@RequiredArgsConstructor
@Transactional
@SpringBootTest
public class TestRatingRepo {
    @Autowired
    private RatingRepository ratingRepository;
    @Test
    void testgetAvgStars(){
        Float avgStars = ratingRepository.getAvgStars(7L);
        Assertions.assertNotNull(avgStars);
    }
}
