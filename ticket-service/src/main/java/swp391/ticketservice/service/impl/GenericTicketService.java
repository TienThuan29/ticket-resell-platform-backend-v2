package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import swp391.entity.*;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.GenericTicketMapper;
import swp391.ticketservice.repository.*;
import swp391.ticketservice.service.def.IGenericTicketService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Nguyen Nhat Truong
 */
@Service
@RequiredArgsConstructor
public class GenericTicketService implements IGenericTicketService {

    private final GenericTicketMapper genericTicketMapper;
    private final MessageConfiguration message;
    private final GenericTicketRepository genericTicketRepository;
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

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
                HttpStatus.OK,
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
        return new ApiResponse<>(HttpStatus.OK,message.SUCCESS_OPERATION);
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
        List<GenericTicket> genericTickets= genericTicketRepository.findByEvent(event);
        List<GenericTicketResponse> genericTicketResponses= genericTickets.stream()
                .map(genericTicketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, genericTicketResponses);
    }
}
