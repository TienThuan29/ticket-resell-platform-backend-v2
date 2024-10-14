package swp391.staffservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.GenericTicket;
import swp391.staffservice.dto.response.GenericTicketResponse;
import swp391.staffservice.utils.DateUtil;
/**
 * Author: Nguyen Nhat Truong
 */
@Component
@RequiredArgsConstructor
public class GenericTicketMapper {

    private final CategoryMapper categoryMapper;

    private final EventMapper eventMapper;

    private final UserMapper userMapper;

    private final TicketMapper ticketMapper;

    public GenericTicketResponse toResponse(GenericTicket genericTicket){
        return GenericTicketResponse
                .builder()
                .id(genericTicket.getId())
                .ticketName(genericTicket.getTicketName())
                .price(genericTicket.getPrice())
                .salePercent(genericTicket.getSalePercent())
                .area(genericTicket.getArea())
                .expiredDateTime(DateUtil.fixDateTime(genericTicket.getExpiredDateTime()))
                .linkEvent(genericTicket.getLinkEvent())
                .description(genericTicket.getDescription())
                .isPaper(genericTicket.getIsPaper())
                .category(categoryMapper.toResponse(genericTicket.getCategory()))
                .event(eventMapper.toResponse(genericTicket.getEvent()))
                .seller(userMapper.toSellerResponse(genericTicket.getSeller()))
                .tickets(
                        genericTicket.getTickets().stream().map(
                                ticketMapper::toResponse
                        ).toList()
                )
                .build();
    }

}
