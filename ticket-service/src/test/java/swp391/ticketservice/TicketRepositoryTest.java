package swp391.ticketservice;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.Event;
import swp391.ticketservice.repository.EventRepository;

import java.util.Collection;
import java.util.List;

@SpringBootTest
public class TicketRepositoryTest {

    EventRepository eventRepository;

    @Test
    void testGetAllEvent() {
        Collection<Event> events = eventRepository.findAll();
        Assertions.assertNotNull(events);
    }

}
