package swp391.staffservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;
import swp391.staffservice.dto.response.TicketResponse;
import swp391.staffservice.utils.DateUtil;
import swp391.staffservice.utils.ImageUtil;

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
