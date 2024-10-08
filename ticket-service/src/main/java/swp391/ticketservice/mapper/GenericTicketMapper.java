package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.ticketservice.repository.PolicyRepository;
import swp391.entity.GenericTicket;
import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.repository.CategoryRepository;
import swp391.ticketservice.repository.EventRepository;
import swp391.ticketservice.repository.UserRepository;
import swp391.ticketservice.utils.DateUtil;

/**
 * Author: Nguyen Nhat Truong
 */
@Component
@RequiredArgsConstructor
public class GenericTicketMapper {

    private final PolicyRepository policyRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final CategoryMapper categoryMapper;

    private final EventMapper eventMapper;

    private final UserMapper userMapper;

    private final TicketMapper ticketMapper;

    public GenericTicket toEntity(GenericTicketRequest genericTicketRequest){
        return GenericTicket
                .builder()
                .ticketName(genericTicketRequest.getGenericTicketName())
                .price(genericTicketRequest.getPrice())
                .salePercent(genericTicketRequest.getSalePercent())
                .area(genericTicketRequest.getArea())
                .expiredDateTime(genericTicketRequest.getExpiredDateTime())
                .description(genericTicketRequest.getDescription())
                .linkEvent(genericTicketRequest.getLinkEvent())
                .isPaper(genericTicketRequest.isPaper())
                .policy(policyRepository.findById(genericTicketRequest.getPolicyId())
                        .orElseThrow(() -> new NotFoundException(""+genericTicketRequest.getPolicyId())))
                .event(eventRepository.findById((genericTicketRequest.getEventId()))
                        .orElseThrow(() -> new NotFoundException(""+genericTicketRequest.getEventId())))
                .category(categoryRepository.findById(genericTicketRequest.getCategoryId())
                        .orElseThrow(() -> new NotFoundException(""+genericTicketRequest.getCategoryId())))
                .seller(userRepository.findById(genericTicketRequest.getSellerId())
                        .orElseThrow(() -> new NotFoundException(""+genericTicketRequest.getSellerId())))
                .build();
    }

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
                .isPaper(genericTicket.isPaper())
                .category(categoryMapper.toResponse(genericTicket.getCategory()))
                .event(eventMapper.toResponse(genericTicket.getEvent()))
                .seller(userMapper.toSellerResponse(genericTicket.getSeller()))
//                .tickets(
//                        genericTicket.getTickets().stream().map(
//                                ticketMapper::toResponse
//                        ).toList()
//                )
                .build();
    }

}
