package swp391.ticketservice;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import swp391.entity.Category;
import swp391.entity.Event;
import swp391.entity.GenericTicket;
import swp391.ticketservice.repository.EventRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
@Transactional

class TicketServiceApplicationTests {
    @Autowired
    private EventRepository eventRepository;
    private Event eventTest = Event.builder()
            .id(10)
            .detail("zsxfzxzdsarge")
            .name("Test event")
            .image(null)
            .startDate(LocalDate.now().toDate())  // Bạn cũng nên chắc chắn rằng start_date được đặt
            .endDate(LocalDate.now().plusDays(1).toDate())  // Thêm end_date không NULL
            .build();
    @Test
    @DisplayName("Should return all happening events (isDeleted = false)")
    @Query("SELECT e FROM Event e WHERE e.isDeleted = false")
    void getHappeningEvents() {
        // Given
        Event event1 = Event.builder()
                .id(1)
                .detail("Test Event 1")
                .name("Event 1")
                .startDate(LocalDate.now().toDate())
                .endDate(LocalDate.now().plusDays(1).toDate())
                .isDeleted(false)
                .build();

        Event event2 = Event.builder()
                .id(2)
                .detail("Test Event 2")
                .name("Event 2")
                .startDate(LocalDate.now().toDate())
                .endDate(LocalDate.now().plusDays(1).toDate())
                .isDeleted(true)  // Deleted event
                .build();

        eventRepository.save(event1);
        eventRepository.save(event2);

        // When
        List<Event> events = eventRepository.getHappeningEvents();

        // Then
        assertThat(events).hasSize(1);
        assertThat(events.get(0).isDeleted()).isFalse();
    }

    @Test
    @DisplayName("Should return events by category")
    void getEventsByCategory() {
        // Given
        Category category = new Category();
        category.setName("Music");
        GenericTicket ticket = new GenericTicket();
        ticket.setCategory(category);
        eventTest.getGenericTickets().add(ticket);
        eventRepository.save(eventTest);

        // When
        List<Event> events = eventRepository.getEventsByCategory("Music");

        // Then
        assertThat(events).hasSize(1);
    }

    @Test
    @DisplayName("Should return events without tickets")
    void getOtherEvent() {
        // Given
        Event eventWithoutTicket = new Event();
        eventRepository.save(eventWithoutTicket);


        GenericTicket ticket = new GenericTicket();
        eventTest.getGenericTickets().add(ticket);
        eventRepository.save(eventTest);

        // When
        List<Event> events = eventRepository.getOtherEvent();

        // Then
        assertThat(events).hasSize(1);
    }
}
