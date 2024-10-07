package swp391.reportservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Ticket;
import swp391.reportservice.dto.response.TicketResponse;
import swp391.reportservice.utils.ImageUtil;

import java.util.Base64;

/**
 * Author: Nguyen Nhat Truong
 */
@Component
@RequiredArgsConstructor
public class TicketMapper {

    public TicketResponse toResponse(Ticket ticket){
        return TicketResponse
                .builder()
                .ticketId(ticket.getId())
                .ticketSerial(ticket.getTicketSerial())
                .image(Base64.getEncoder().encodeToString(ImageUtil.decompressImage(ticket.getImage())))
                .isChecked(ticket.isChecked())
                .isBought(ticket.isBought())
                .isValid(ticket.isValid())
                .note(ticket.getNote())
                .process(ticket.getProcess())
                .boughtDate(ticket.getBoughtDate())
                .genericTicketId(ticket.getGenericTicket().getId())
                .verifyStaffId( ticket.getVerifyStaff() == null ? null : ticket.getVerifyStaff().getId() )
                .buyerId(ticket.getBuyerId())
                .build();
    }
}
