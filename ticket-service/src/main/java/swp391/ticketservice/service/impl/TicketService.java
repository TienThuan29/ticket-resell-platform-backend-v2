package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.GenericTicket;
import swp391.entity.Ticket;
import swp391.entity.User;
import swp391.entity.fixed.GeneralProcess;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.TicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.dto.response.TicketResponse;
import swp391.ticketservice.exception.def.InvalidProcessException;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.GenericTicketMapper;
import swp391.ticketservice.mapper.TicketMapper;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.StaffRepository;
import swp391.ticketservice.repository.TicketRepository;
import swp391.ticketservice.repository.UserRepository;
import swp391.ticketservice.service.def.ITicketService;
import swp391.ticketservice.utils.ImageUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: Nguyen Nhat Truong
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    private final StaffRepository staffRepository;

    private final MessageConfiguration message;

    private final UserRepository userRepository;

    private final GenericTicketRepository genericTicketRepository;

    private final GenericTicketMapper genericTicketMapper;

    @Override
    public ApiResponse<List<TicketResponse>> getAll() {
        List<Ticket> tickets= ticketRepository.findAll();
        List<TicketResponse> ticketResponses= tickets.stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, ticketResponses);
    }

    @Override
    public ApiResponse<TicketResponse> getById(Long ticketId) {
        Ticket ticket= ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException(message.INVALID_TICKET+" :"+ticketId));
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, ticketMapper.toResponse(ticket));
    }

    @Override
    public ApiResponse<TicketResponse> create(TicketRequest ticketRequest, MultipartFile file) throws IOException {
        //ticketRequest.setProcess(GeneralProcess.WAITING.toString());
        var staff = staffRepository.getStaffHasMinTicket().orElseThrow(
                () -> new NotFoundException(message.INVALID_STAFF)
        );

        Ticket ticket= ticketMapper.toEntity(ticketRequest);
        ticket.setImage(ImageUtil.compressImage(file.getBytes()));
        ticket.setBought(Boolean.FALSE);
        ticket.setChecked(Boolean.FALSE);
        ticket.setValid(Boolean.FALSE);
        ticket.setProcess(GeneralProcess.WAITING);
        ticket.setVerifyStaff(staff);

        ticketRepository.save(ticket);
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, ticketMapper.toResponse(ticket));
    }

    @Override
    public ApiResponse<?> markBought(Long id) {
        ZoneId zoneId = ZoneId.of("UTC+7");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        Date boughtDate = Date.from(localDateTime.atZone(zoneId).toInstant());

        Ticket ticket= ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(message.INVALID_TICKET+" :"+id));
        ticket.setBought(Boolean.TRUE);
        ticket.setBoughtDate(boughtDate);
        ticketRepository.save(ticket);
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION);
    }

    @Override
    public ApiResponse<?> markStaffCheck(Long id, Long staffId) {
        Ticket ticket= ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(message.INVALID_TICKET+" :"+id));
        ticket.setChecked(Boolean.TRUE);
        ticket.setVerifyStaff(staffRepository.findById(staffId)
                .orElseThrow(() -> new NotFoundException(message.INVALID_STAFF+" :"+id)));
        ticketRepository.save(ticket);
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION);
    }

    @Override
    public ApiResponse<?> updateProcess(Long id, String process) {
        Ticket ticket= ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(message.INVALID_TICKET+" :"+id));
        ticket.setProcess(getProcess(process)
                .orElseThrow(() -> new InvalidProcessException(message.INVALID_PROCESS+" :"+process)));
        ticketRepository.save(ticket);
        return new ApiResponse<>(HttpStatus.OK,message.SUCCESS_OPERATION);
    }

    @Override
    public ApiResponse<List<TicketResponse>> getTicketsByProcess(GeneralProcess process) {
        List<Ticket> tickets= ticketRepository.findByProcess(process).get();
        List<TicketResponse> ticketResponses= tickets.stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, ticketResponses);
    }

    private Optional<GeneralProcess> getProcess(String process){
        try {
            return Optional.ofNullable(GeneralProcess.valueOf(process));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public ApiResponse<List<TicketResponse>> getGenericTicketWithTicketsOfSeller(Long sellerId) {

        User seller = userRepository.findById(sellerId).get();

        List<GenericTicket> genericTickets = genericTicketRepository.findBySeller(seller);

        List<Ticket> ticketList = new ArrayList<>();

        for (GenericTicket genTicket : genericTickets){
            ticketList.addAll(ticketRepository.findByGenericTicket(genTicket));
        }

        List<TicketResponse> ticketResponses = ticketList.stream().map(ticketMapper::toResponse).toList();
        for (TicketResponse ticketResponse : ticketResponses) {
            GenericTicket genericTicket = genericTicketRepository.findById(ticketResponse.getGenericTicketId()).get();
            ticketResponse.setGenericTicketObject(genericTicketMapper.toResponse(genericTicket));
        }

        return new ApiResponse<>(HttpStatus.OK, "", ticketResponses);
    }

}
