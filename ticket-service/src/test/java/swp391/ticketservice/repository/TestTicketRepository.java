package swp391.ticketservice.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.*;
import swp391.ticketservice.repository.CategoryRepository;
import swp391.ticketservice.repository.EventRepository;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.HashtagRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestTicketRepository {

//    @Autowired
//    private CategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

//    @Autowired
//    private GenericTicketRepository genericTicketRepository;
//
//    @Autowired
//    private HashtagRepository hashtagRepository;
//
//    private Category category;

//    @BeforeEach
//    void setUp() {
//        List<Category> categoryList = categoryRepository.getUsingCategories();
//        category = categoryList.get(0);
//    }
//
//    @Test
//    void testGetAvailableCategory() {
//        List<Category> listCategory = categoryRepository.getUsingCategories();
//        Assertions.assertNotNull(listCategory);
//    }

    @Test
    void testGetHappenningEvent() {
        List<Event> listEvent = eventRepository.getHappeningEvents();
        Assertions.assertNotNull(listEvent);
    }

//    @Test
//    void testGetEventByCategory() {
//        List<Event> eventList = eventRepository.getEventsByCategory(category.getName());
//        Assertions.assertNotNull(eventList);
//    }
//
//    @Test
//    void testGetEventByFilter() throws Exception {
//        String name = "Giai Điệu Mùa Thu";
//        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("28/10/2024");
//        List<Integer> hashtags = List.of(1);
//
//        List<Event> eventList = eventRepository.getEventByFilter(name, startDate, hashtags);
//        Assertions.assertNotNull(eventList);
//
//        eventList.forEach(event -> {
//            Assertions.assertTrue(event.getName().toLowerCase().contains(name.toLowerCase()));
//            Assertions.assertEquals(startDate, event.getStartDate());
//        });
//    }
//
//    @Test
//    void testdeleteHashtagLogic() {
//        hashtagRepository.deleteHashtagLogic(1);
//        Optional<Hashtag> deleteHashtag = hashtagRepository.findById(1);
//        Assertions.assertNotNull(deleteHashtag);
//    }
}
