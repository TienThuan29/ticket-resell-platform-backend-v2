package swp391.ticketservice.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@DataJpaTest
@RequiredArgsConstructor
@Transactional
@SpringBootConfiguration
public class TestGenericTicketRepository {
//
//    private final CategoryRepository categoryRepo;
//
//    @Test
//    public void getAvailableCategory(){
//        var cateList = categoryRepo.getUsingCategories();
//        Assertions.assertNotNull(cateList);
//    }

}
