package swp391.ticketservice.Repository;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GenericTicketRepository genericTicketRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        List<Category> categoryList = categoryRepository.getUsingCategories();
        category = categoryList.get(0);
    }

    @Test
    void testGetAvailableCategory() {
        List<Category> listCategory = categoryRepository.getUsingCategories();
        Assertions.assertNotNull(listCategory);
    }

    @Test
    @Description("Test to get all happening events")
    @Step("Executing testGetHappeningEvent")
    @Story("Retrieve all events that are currently happening")
    void testGetHappenningEvent() {
        List<Event> listEvent = eventRepository.getHappeningEvents();
        Assertions.assertNotNull(listEvent, "The list of events should not be null");
    }

    @Test
    @Description("Test to get events by category")
    @Step("Executing testGetEventByCategory")
    @Story("Retrieve events filtered by category")
    void testGetEventByCategory() {
        List<Event> eventList = eventRepository.getEventsByCategory(category.getName());
        Assertions.assertNotNull(eventList, "The list of events should not be null");
    }

    @Test
    @Description("Test to get events by filter criteria")
    @Step("Executing testGetEventByFilter")
    @Story("Retrieve events based on name, start date, and hashtags")
    void testGetEventByFilter() throws Exception {
        String name = "Giai Điệu Mùa Thu";
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("28/10/2024");
        List<Integer> hashtags = List.of(1);

        List<Event> eventList = eventRepository.getEventByFilter(name, startDate, hashtags);
        Assertions.assertNotNull(eventList, "The list of events should not be null");

        eventList.forEach(event -> {
            Assertions.assertTrue(event.getName().toLowerCase().contains(name.toLowerCase()), "Event name should match the filter");
            Assertions.assertEquals(startDate, event.getStartDate(), "Event start date should match the filter");
        });
    }

    @Test
    @Description("Test to delete a hashtag logically")
    @Step("Executing testDeleteHashtagLogic")
    @Story("Delete a hashtag and verify it is no longer present")
    void testdeleteHashtagLogic() {
        hashtagRepository.deleteHashtagLogic(1);
        Optional<Hashtag> deleteHashtag = hashtagRepository.findById(1);
        Assertions.assertNotNull(deleteHashtag, "The deleted hashtag should not be found");
    }
}
