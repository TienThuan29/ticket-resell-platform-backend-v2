package swp391.ticketservice.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.ticketservice.repository.RatingRepository;

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
