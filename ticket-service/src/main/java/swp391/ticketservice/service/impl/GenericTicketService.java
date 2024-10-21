package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import swp391.entity.*;
import swp391.ticketservice.config.ConstantConfiguration;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.GenericTicketFilter;
import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.request.NotificationRequest;
import swp391.ticketservice.dto.request.OrderTicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.dto.response.OrderTicketResponse;
import swp391.ticketservice.dto.response.TicketResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.GenericTicketMapper;
import swp391.ticketservice.mapper.OrderTicketMapper;
import swp391.ticketservice.mapper.TicketMapper;
import swp391.ticketservice.repository.*;
import swp391.ticketservice.service.def.IGenericTicketService;
import swp391.ticketservice.service.def.NotificationServiceFeign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Nguyen Nhat Truong
 * Author: Nguyen Tien Thuan
 */
@Component
@RequiredArgsConstructor
public class GenericTicketService implements IGenericTicketService {

    private final GenericTicketMapper genericTicketMapper;

    private final MessageConfiguration message;

    private final ConstantConfiguration constant;

    private final GenericTicketRepository genericTicketRepository;

    private final PolicyRepository policyRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final CategoryRepository categoryRepository;

    private final OrderTicketMapper orderTicketMapper;

    private final OrderTicketRepository orderTicketRepository;

    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    private final NotificationServiceFeign notificationServiceFeign;

    @Override
    public ApiResponse<GenericTicketResponse> create(GenericTicketRequest genericTicketRequest) {

        Policy policy= policyRepository.findById(genericTicketRequest.getPolicyId())
                .orElseThrow(() -> new NotFoundException(message.INVALID_POLICY+" :"+genericTicketRequest.getPolicyId()));
        Category category= categoryRepository.findById(genericTicketRequest.getCategoryId())
                .orElseThrow(() -> new NotFoundException(message.INVALID_CATEGORY+" :"+genericTicketRequest.getCategoryId()));
        Event event= eventRepository.findById(genericTicketRequest.getEventId())
                .orElseThrow(() -> new NotFoundException(message.INVALID_EVENT+" :"+genericTicketRequest.getEventId()));
        User seller= userRepository.findById(genericTicketRequest.getSellerId())
                .orElseThrow(() -> new NotFoundException(message.INVALID_BUYER+" :"+genericTicketRequest.getSellerId()));
        if(!seller.getIsSeller())
            throw new NotFoundException(message.INVALID_SELLER+" :"+seller.getId());

        GenericTicket genericTicket= genericTicketMapper.toEntity(genericTicketRequest);
        genericTicket.setPolicy(policy);
        genericTicket.setCategory(category);
        genericTicket.setEvent(event);
        genericTicket.setSeller(seller);

        return new ApiResponse<>(
                HttpStatus.CREATED,
                message.SUCCESS_OPERATION,
                genericTicketMapper.toResponse(genericTicketRepository.save(genericTicket))
        );
    }

    @Override
    public ApiResponse<GenericTicketResponse> updateAllFields(Long id, GenericTicketRequest genericTicketRequest) {
        GenericTicket newGenericTicket= genericTicketMapper.toEntity(genericTicketRequest);
        newGenericTicket.setId(id);
        return new ApiResponse<>(
                HttpStatus.OK,
                message.UPDATE_GENERIC_SUCCESS,
                genericTicketMapper.toResponse(genericTicketRepository.save(newGenericTicket))
        );
    }

    @Override
    public ApiResponse<?> updatePriceAndExpiredDate(Long id, Long price, Date date) {
        GenericTicket genericTicket= genericTicketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(message.INVALID_GENERICTICKET+" :"+id));
        genericTicket.setPrice(price);
        genericTicket.setExpiredDateTime(date);
        genericTicketRepository.save(genericTicket);
        return new ApiResponse<>(HttpStatus.NO_CONTENT,message.SUCCESS_OPERATION);
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getAll() {
        List<GenericTicket> genericTickets = genericTicketRepository.findAll();
        List<GenericTicketResponse> genericTicketResponses= genericTickets.stream()
                .map(genericTicketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, genericTicketResponses);
    }

    @Override
    public ApiResponse<GenericTicketResponse> getById(Long id) {
        GenericTicket genericTicket= genericTicketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(message.INVALID_GENERICTICKET+" :"+id));
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, genericTicketMapper.toResponse(genericTicket));
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getByCategory(Integer categoryId) {
        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(message.INVALID_CATEGORY+" :"+categoryId));
        List<GenericTicket> genericTickets= genericTicketRepository.findByCategory(category);
        List<GenericTicketResponse> genericTicketResponses= genericTickets.stream()
                .map(genericTicketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, genericTicketResponses);
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getByEvent(Integer eventId) {
        Event event= eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(message.INVALID_EVENT+" :"+eventId));
        List<GenericTicket> genericTickets= genericTicketRepository.findAllValidGenericTicket(event.getId());
        List<GenericTicketResponse> genericTicketResponses= genericTickets.stream()
                .map(genericTicketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, genericTicketResponses);
    }

    @Override
    public ApiResponse<Integer> getTotalTicketsInGenericTicket(Long genericTicketId) {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                genericTicketRepository.getTotalTicketsInGenericTicket(genericTicketId)
        );
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getAllGenericTicketBySeller(Long sellerId) {
        User seller = userRepository.findById(sellerId).get();
        List<GenericTicketResponse> genericTicketResponses = genericTicketRepository.findBySeller(seller)
                .stream().map(genericTicketMapper::toResponse).toList();
        return new ApiResponse<>(
                HttpStatus.OK, "",
                genericTicketResponses
        );
    }

    @Override
    public ApiResponse<List<GenericTicketResponse>> getByFilter(GenericTicketFilter genericTicketFilter) {
        var genericTickets= genericTicketRepository.findByFilters(
                genericTicketFilter.getMinPrice(),
                genericTicketFilter.getMaxPrice(),
                genericTicketFilter.getTicketName(),
                genericTicketFilter.getArea(),
                genericTicketFilter.isPaper()
        );

        var genericTicketResponses= genericTickets.stream()
                .map(genericTicketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, "", genericTicketResponses);
    }

    @Override
    public ApiResponse<?> orderTicket(OrderTicketRequest orderTicketRequest) {
        try {
            orderTicketRepository.save(orderTicketMapper.toEntity(orderTicketRequest));
            var user = userRepository.findById(orderTicketRequest.getBuyerId()).orElseThrow(
                    () -> new NotFoundException(message.INVALID_BUYER)
            );
            user.setBalance(user.getBalance() - orderTicketRequest.getTotalPrice());
            userRepository.save(user);
        }
        catch (Exception ex) {
            System.out.println(ex);
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, message.FAIL_ORDER_TICKET, null);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_ORDER_TICKET, null);
    }

    @Override
    public ApiResponse<?> cancelTicketOrder(String orderNo, Long senderId) {
        var orderTicket = orderTicketRepository.findByOrderNo(orderNo).orElseThrow(
                () -> new NotFoundException(message.ERROR_ORDER_TICKET_NOT_FOUND)
        );
        try {
            orderTicket.setCanceled(Boolean.TRUE);
            orderTicketRepository.save(orderTicket);
            // Send cancel order notification to seller
            notificationServiceFeign.sendCancelOrderNotification(
                    NotificationRequest.builder()
                            .senderId(senderId)
                            .receiverId(orderTicket.getGenericTicket().getSeller().getId())
                            .header(constant.NOTIFICATION_TEMPLATE.VERIFICATION_TICKET_HEADER)
                            .subHeader(constant.NOTIFICATION_TEMPLATE.VERIFICATION_TICKET_SUBHEADER)
                            .content(constant.NOTIFICATION_TEMPLATE.VERIFICATION_TICKET_CONTENT)
                            .build()
            );
        }
        catch (Exception ex) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, message.ERROR_CANCEL_TICKET_ORDER, null);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_CANCEL_TICKET_ORDER, null);
    }

    @Override
    public ApiResponse<List<OrderTicketResponse>> getProcessingOrderTicket(Long userId) {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                orderTicketRepository.getProcessingOrderTicket(userId)
                        .stream().map(orderTicketMapper::toResponse).toList()
        );
    }

    @Override
    public ApiResponse<List<OrderTicketResponse>> getCanceledOrderTicket(Long userId) {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                orderTicketRepository.getCanceledOrderTicket(userId)
                        .stream().map(orderTicketMapper::toResponse).toList()
        );
    }

    @Override
    public ApiResponse<List<OrderTicketResponse>> getAllOrderTicketRequest(Long sellerId) {
        List<OrderTicketResponse> orderTicketResponses = orderTicketRepository.getAllRequestOrderTicket(sellerId)
                .stream().map(orderTicketMapper::toResponseWithBuyer).toList();
        for (OrderTicketResponse orTicketResp : orderTicketResponses) {
            orTicketResp.setTicketList(
                    ticketRepository.getNotBoughtTicketByGenericTicketNotBought(orTicketResp.getGenericTicket().getId())
                            .stream().map(ticketMapper::toResponse).toList()
            );
        }
        return new ApiResponse<>(
                HttpStatus.OK, "",
                orderTicketResponses
        );
    }
}
