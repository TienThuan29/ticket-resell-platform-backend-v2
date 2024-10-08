package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.TicketRequest;
import swp391.ticketservice.dto.response.TicketResponse;
import swp391.ticketservice.exception.def.InvalidProcessException;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.UserRepository;
import swp391.ticketservice.utils.DateUtil;
import swp391.ticketservice.utils.ImageUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

/**
 * Author: Nguyen Nhat Truong
 */
@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final GenericTicketRepository genericTicketRepository;
    private final MessageConfiguration messageConfig;

    public Ticket toEntity(TicketRequest ticketRequest) throws InvalidProcessException {
        return  Ticket
                .builder()
                .ticketSerial(ticketRequest.getTicketSerial())
//                .image(ImageUtil.compressImage(ticketRequest.getImage()))
                .isChecked(Boolean.FALSE)
                .isBought(Boolean.FALSE)
                .isValid(Boolean.FALSE)
                .note(ticketRequest.getNote())
                .process(GeneralProcess.WAITING)
                .genericTicket(genericTicketRepository.findById(ticketRequest.getGenericTicketId())
                        .orElseThrow(() -> new NotFoundException(
                                messageConfig.INVALID_GENERICTICKET+" :"+ticketRequest.getGenericTicketId())
                        )
                )
                .build();
    }

    public TicketResponse toResponse(Ticket ticket){
        return TicketResponse
                .builder()
                .ticketId(ticket.getId())
                .ticketSerial(ticket.getTicketSerial())
                .image(
                        ImageUtil.decompressImage(ticket.getImage())
                )
                .isChecked(ticket.isChecked())
                .isBought(ticket.isBought())
                .isValid(ticket.isValid())
                .note(ticket.getNote())
                .process(ticket.getProcess())
                .boughtDate(ticket.getBoughtDate() != null ? DateUtil.fixDateTime(ticket.getBoughtDate()) : null)
                .genericTicketId(ticket.getGenericTicket().getId())
                .verifyStaffId( ticket.getVerifyStaff() == null ? null : ticket.getVerifyStaff().getId() )
                .buyerId(ticket.getBuyerId())
                .build();
    }
}
