package swp391.staffservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.GenericTicket;
import swp391.staffservice.repository.GenericTicketRepository;

import java.util.List;

@SpringBootTest
public class TestStaffRepo {
    @Autowired
    private GenericTicketRepository genericTicketRepository;

    @Test
    void testgetAllGenericTicketOfStaff(){
        List<GenericTicket> ticketList = genericTicketRepository.getAllGenericTicketOfStaff(7L);
        Assertions.assertNotNull(ticketList);
    }
}
