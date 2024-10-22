package swp391.staffservice;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.GenericTicket;
import swp391.entity.Staff;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;
import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.dto.response.GenericTicketResponse;
import swp391.staffservice.repository.GenericTicketRepository;
import swp391.staffservice.repository.StaffRepository;
import swp391.staffservice.repository.TicketRepository;
import swp391.staffservice.service.IStaffService;

import java.util.List;

@SpringBootTest
public class StaffServiceApplicationTests {

    @Autowired
    private IStaffService staffService;

    @Autowired
    private GenericTicketRepository genericTicketRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private TicketRepository ticketRepository;

    private GenericTicket genericTicket;
    private Ticket ticket;
    private Staff staff;

    @BeforeEach
    void setUp() {
        genericTicket = genericTicketRepository.findAll().get(0);
        staff = staffRepository.findAll().get(1);
        ticket = ticketRepository.findAll().get(0);
    }

    @Test
    @Description("Test to get all generic tickets that need verification")
    @Step("Executing testGetAllGenericTicketNeedVerify")
    @Story("Staff should be able to retrieve all generic tickets that need verification")
    void testGetAllGenericTicketNeedVerify() {
        ApiResponse<List<GenericTicketResponse>> response = staffService.getAllGenericTicketNeedVerify(7L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value());
//        Assertions.assertFalse(response.getObject().isEmpty(), "The list of generic tickets should not be empty");
    }

    @Test
    @Description("Test to mark a ticket as valid")
    @Step("Executing testMarkValidTicket")
    @Story("Staff should be able to mark a ticket as valid")
    void testMarkValidTicket() {
        ApiResponse<?> response = staffService.markValidTicket(ticket.getId());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value(), "The ticket should be marked as valid successfully");

        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).orElse(null);
        Assertions.assertNotNull(updatedTicket);
        Assertions.assertTrue(updatedTicket.isValid(), "The ticket should be marked as valid");
        Assertions.assertTrue(updatedTicket.isChecked(), "The ticket should be marked as checked");
        Assertions.assertEquals(GeneralProcess.SELLING, updatedTicket.getProcess(), "The ticket process should be set to SELLING");
    }

    @Test
    @Description("Test to mark a ticket as invalid")
    @Step("Executing testMarkInvalidTicket")
    @Story("Staff should be able to mark a ticket as invalid")
    void testMarkInvalidTicket() {
        String note = "Invalid ticket due to incorrect details";
        ApiResponse<?> response = staffService.markInvalidTicket(ticket.getId(), note);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value(), "The ticket should be marked as invalid successfully");

        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).orElse(null);
        Assertions.assertNotNull(updatedTicket);
        Assertions.assertFalse(updatedTicket.isValid(), "The ticket should be marked as invalid");
        Assertions.assertTrue(updatedTicket.isChecked(), "The ticket should be marked as checked");
        Assertions.assertEquals(GeneralProcess.REJECTED, updatedTicket.getProcess(), "The ticket process should be set to REJECTED");
        Assertions.assertEquals(note, updatedTicket.getNote(), "The ticket note should match the provided note");
    }
}
