package swp391.ticketservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.Staff;
import swp391.ticketservice.repository.StaffRepository;

@Slf4j
@SpringBootTest
class TicketServiceApplicationTests {

    @Autowired
    private StaffRepository staffRepository;

    @Test
    void testGetStaffHasMinTicket() {
        var staff = staffRepository.getStaffHasMinTicket().get();
        Assertions.assertNotNull(staff);
        log.info("Staff id: "+staff.getId());
    }

}
