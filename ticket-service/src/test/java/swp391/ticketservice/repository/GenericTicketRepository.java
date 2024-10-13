package swp391.ticketservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@RequiredArgsConstructor
public class GenericTicketRepository {

    private final GenericTicketRepository genericTicketRepo;

}
