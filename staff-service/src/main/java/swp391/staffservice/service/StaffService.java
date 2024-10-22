package swp391.staffservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;
import swp391.staffservice.configuration.ConstantConfiguration;
import swp391.staffservice.configuration.MessageConfiguration;
import swp391.staffservice.dto.request.NotificationRequest;
import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.dto.response.GenericTicketResponse;
import swp391.staffservice.dto.response.TicketResponse;
import swp391.staffservice.exception.def.NotFoundException;
import swp391.staffservice.mapper.GenericTicketMapper;
import swp391.staffservice.repository.GenericTicketRepository;
import swp391.staffservice.repository.TicketRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffService implements IStaffService {

    private final GenericTicketMapper genericTicketMapper;

    private final GenericTicketRepository genericTicketRepository;

    private final TicketRepository ticketRepository;

    private final MessageConfiguration messageConfig;

    private final NotificationServiceFeign notificationServiceFeign;

    private final ConstantConfiguration constant;

    @Override
    public ApiResponse<?> verifyTicket(Long id) {
//        Ticket ticket = ticketRepository
        return null;
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getAllGenericTicketNeedVerify(Long staffId) {
        List<GenericTicketResponse> genericTicketResponses = genericTicketRepository.getAllGenericTicketOfStaff(staffId)
                .stream().map(genericTicketMapper::toResponse).toList();
        genericTicketResponses.forEach(genericTicket -> {
            genericTicket.setIsValidatedAll(
                    genericTicket.getTickets().stream().allMatch(TicketResponse::isChecked)
            );
        });
        return new ApiResponse<>(HttpStatus.OK, "", genericTicketResponses);
    }

    @Override
    public ApiResponse<?> markValidTicket(Long ticketId) {
        var ticket = getTicketFromRepo(ticketId);
        try {
            ticket.setValid(Boolean.TRUE);
            ticket.setChecked(Boolean.TRUE);
            ticket.setProcess(GeneralProcess.SELLING);
            ticketRepository.save(ticket);
            // Send notification
            notificationServiceFeign.sendVerificationNotification(
                    NotificationRequest.builder()
                            .senderId(ticket.getVerifyStaff().getId())
                            .receiverId(ticket.getGenericTicket().getSeller().getId())
                            .header(constant.NOTIFICATION_TEMPLATE.VERIFICATION_TICKET_HEADER)
                            .subHeader(constant.NOTIFICATION_TEMPLATE.VERIFICATION_TICKET_SUBHEADER + ticket.getTicketSerial())
                            .content(constant.NOTIFICATION_TEMPLATE.VERIFICATION_TICKET_CONTENT)
                            .build()
            );
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_WHILE_VERIFY_TICKET, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.MESSAGE_VERIFY_TICKET_SUCCESS, null);
    }

    @Override
    public ApiResponse<?> markInvalidTicket(Long ticketId, String note) {
        var ticket = getTicketFromRepo(ticketId);
        try {
            ticket.setValid(Boolean.FALSE);
            ticket.setChecked(Boolean.TRUE);
            ticket.setProcess(GeneralProcess.REJECTED);
            ticket.setNote(note);
            ticketRepository.save(ticket);
        }
        catch (Exception ex) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_WHILE_VERIFY_TICKET, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.MESSAGE_VERIFY_TICKET_SUCCESS, null);
    }

    private Ticket getTicketFromRepo(Long ticketId){
        return ticketRepository.findById(ticketId).orElseThrow(
                () -> new NotFoundException(messageConfig.ERROR_NOT_FOUND_TICKET)
        );
    }

}
