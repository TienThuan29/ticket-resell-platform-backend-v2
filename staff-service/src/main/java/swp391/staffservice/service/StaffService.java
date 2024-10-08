package swp391.staffservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swp391.entity.Ticket;
import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class StaffService implements IStaffService {

    private final TicketRepository ticketRepository;

    @Override
    public ApiResponse<?> verifyTicket(Long id) {
//        Ticket ticket = ticketRepository
        return null;
    }

}
