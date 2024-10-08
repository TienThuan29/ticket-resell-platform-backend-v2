package swp391.staffservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.dto.response.GenericTicketResponse;
import swp391.staffservice.mapper.GenericTicketMapper;
import swp391.staffservice.repository.GenericTicketRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService implements IStaffService {

    private final GenericTicketMapper genericTicketMapper;

    private final GenericTicketRepository genericTicketRepository;

    @Override
    public ApiResponse<?> verifyTicket(Long id) {
//        Ticket ticket = ticketRepository
        return null;
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getAllGenericTicketNeedVerify(Long staffId) {
        return new ApiResponse<>(HttpStatus.OK, "",
                genericTicketRepository.getAllGenericTicketOfStaff(staffId)
                        .stream().map(genericTicketMapper::toResponse).toList()
        );
    }

}
