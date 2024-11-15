package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.*;
import swp391.entity.embedable.OrderTicketID;
import swp391.entity.fixed.GeneralProcess;
import swp391.entity.fixed.TransactionType;
import swp391.ticketservice.config.ConstantConfiguration;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.AcceptOrDenySellingRequest;
import swp391.ticketservice.dto.request.NotificationRequest;
import swp391.ticketservice.dto.request.TicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.dto.response.TicketResponse;
import swp391.ticketservice.exception.def.InvalidProcessException;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.GenericTicketMapper;
import swp391.ticketservice.mapper.TicketMapper;
import swp391.ticketservice.mapper.UserMapper;
import swp391.ticketservice.repository.*;
import swp391.ticketservice.service.def.ITicketService;
import swp391.ticketservice.service.def.NotificationServiceFeign;
import swp391.ticketservice.utils.ImageUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
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
    private final TransactionRepository transactionRepository;
    private final OrderTicketRepository orderTicketRepository;
    private final UserMapper userMapper;
    private final RatingRepository ratingRepository;
    private final NotificationServiceFeign notificationServiceFeign;
    private final ConstantConfiguration constant;

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
        //ticketRequest.setImage(file.getBytes());
//        ticketRequest.setImage(file.getBytes());
        log.info(file.getName());
        Ticket ticket= ticketMapper.toEntity(ticketRequest);
        ticket.setImage(ImageUtil.compressImage(file.getBytes()));
//        ticket.setBought(Boolean.FALSE);
//        ticket.setChecked(Boolean.FALSE);
//        ticket.setValid(Boolean.FALSE);
//        ticket.setProcess(GeneralProcess.WAITING);
        ticket.setVerifyStaff(staff);

        ticketRepository.save(ticket);
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_OPERATION, ticketMapper.toResponse(ticket));
    }

    @Override
    public ApiResponse<?> markBought(Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date boughtDate = Date.from(zonedDateTime.toInstant());

        try {
            Ticket ticket= ticketRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(message.INVALID_TICKET+" :"+id));
            ticket.setBought(Boolean.TRUE);
            ticket.setBoughtDate(boughtDate);
            ticket.setProcess(GeneralProcess.SUCCESS);
            ticketRepository.save(ticket);
        }
        catch (Exception ex) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, message.ERROR_MARK_IS_BOUGHT_TICKET);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_MARK_IS_BOUGHT_TICKET);
    }

    @Override
    public ApiResponse<?> deleteTicketFromShop(Long id) {
        try {
            var ticket = ticketRepository.findById(id).orElseThrow(null);
            ticketRepository.deleteById(id);
            // Check the number of ticket in generic ticket to delete generic ticket
            Integer numOfTicketInGenericTicket = genericTicketRepository.getTotalTicketsInGenericTicket(
                    ticket.getGenericTicket().getId()
            );
            if (numOfTicketInGenericTicket == null || numOfTicketInGenericTicket == 0) {
                genericTicketRepository.deleteById(ticket.getGenericTicket().getId());
            }
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, message.ERROR_DELETE_TICKET_FROM_SHOP, null);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_DELETE_TICKET_FROM_SHOP, null);
    }

    @Override
    public ApiResponse<?> markDeliveredPaperTicket(Long ticketId) {
        try {
            Ticket ticket= ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new NotFoundException(message.INVALID_TICKET+" :"+ticketId));
            ticket.setProcess(GeneralProcess.SUCCESS);
            ticketRepository.save(ticket);
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, message.ERROR_MARK_DELIVERED_PAPER_TICKET);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_MARK_DELIVERED_PAPER_TICKET);
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

    @Override
    public ApiResponse<?> acceptToSellTicket(AcceptOrDenySellingRequest request) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date boughtDate = Date.from(zonedDateTime.toInstant());
        try {
            // Update order ticket
            OrderTicket orderTicket = getOrderTicketById(
                    request.getGenericTicketId(), request.getBuyerId(), request.getOrderNo()
            );

            if (!orderTicket.isAccepted() && !orderTicket.getNote().isEmpty()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Đơn hàng đã được từ chối");
            }
            else if (orderTicket.isAccepted()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Đơn hàng này đã được xác nhận");
            }
            else if (orderTicket.isCanceled()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Đơn hàng này đã bị hủy bởi người mua!");
            }

            // Automatic transact tickets to buyer
            List<Ticket> notBoughtTickets = ticketRepository.getNotBoughtTicketByGenericTicketNotBought(
                    request.getGenericTicketId()
            );
            if (request.getQuantity() <= notBoughtTickets.size()) {
                    for (int i = 1; i <= request.getQuantity(); i++) {
                        notBoughtTickets.get(i-1).setBought(Boolean.TRUE);
                        notBoughtTickets.get(i-1).setBuyerId(request.getBuyerId());
                        notBoughtTickets.get(i-1).setBoughtDate(boughtDate);
                        notBoughtTickets.get(i-1).setProcess(
                                request.getIsPaper() ? GeneralProcess.DELIVERING : GeneralProcess.SUCCESS
                        );
                    }


                orderTicket.setAccepted(request.getIsAccepted());
                orderTicketRepository.save(orderTicket);
                ticketRepository.saveAll(notBoughtTickets);

                if (!request.getIsPaper()) {
                    // Update Transaction table
                    Transaction buyerTrans = Transaction.builder()
                            .amount(request.getTotalPrice())
                            .transDate(boughtDate)
                            .isDone(Boolean.TRUE)
                            .type(TransactionType.BUYING)
                            .user(getUserById(request.getBuyerId()))
                            .transactionNo(randomTransactionNo())
                            .build();
                    transactionRepository.save(buyerTrans);

                    // Add amount for admin
//                    staffRepository.updateBalanceOfAdmin(request.getTotalPrice());

                    Transaction sellerTrans = Transaction.builder()
                            .amount(request.getTotalPrice())
                            .transDate(boughtDate)
                            .isDone(Boolean.FALSE)
                            .type(TransactionType.SELLING)
                            .user(getUserById(request.getSellerId()))
                            .transactionNo(randomTransactionNo())
                            .build();
                    transactionRepository.save(sellerTrans);
                }

                notificationServiceFeign.sendAcceptToSellNotification(
                        NotificationRequest.builder()
                                .senderId(request.getSellerId())
                                .receiverId(request.getBuyerId())
                                .header(constant.NOTIFICATION_TEMPLATE.ACCEPT_TO_SELL_TICKET_HEADER)
                                .subHeader(constant.NOTIFICATION_TEMPLATE.ACCEPT_TO_SELL_SUBHEADER)
                                .content(constant.NOTIFICATION_TEMPLATE.ACCEPT_TO_SELL_CONTENT)
                                .build()
                );
            }
            else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, message.ERROR_NOT_ENOUGH_TICKET_TO_BUY);
            }
        }
        catch (Exception exception) {
            log.info(exception.toString());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, message.ERROR_ACCEPT_TO_SELL_TICKET, null);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_ACCEPT_TO_SELL_TICKET, null);
    }

    @Override
    public ApiResponse<?> denyToSellTicket(AcceptOrDenySellingRequest request) {
        try {
            // Update order ticket
            OrderTicket orderTicket = getOrderTicketById(
                    request.getGenericTicketId(), request.getBuyerId(), request.getOrderNo()
            );

            if (!orderTicket.isAccepted() && !orderTicket.getNote().isEmpty()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Đơn hàng đã được từ chối");
            }
            else if (orderTicket.isAccepted()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Đơn hàng này đã được xác nhận");
            }
            else if (orderTicket.isCanceled()) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Đơn hàng này đã bị hủy bởi người mua!");
            }

            orderTicket.setAccepted(request.getIsAccepted());
            orderTicket.setNote(request.getNote());
            orderTicketRepository.save(orderTicket);

            // Refund amount for buyer
            var buyer = getUserById(request.getBuyerId());
            buyer.setBalance(buyer.getBalance() + request.getTotalPrice());
            userRepository.save(buyer);

            // Minus balance of admin
            // Cho nay can xem lai
            staffRepository.updateBalanceOfAdmin(-1 * request.getTotalPrice());

            notificationServiceFeign.sendRejectToSellNotification(
                    NotificationRequest.builder()
                            .senderId(getUserById(request.getSellerId()).getId())
                            .receiverId(getUserById(request.getBuyerId()).getId())
                            .header(constant.NOTIFICATION_TEMPLATE.REJECT_TO_SELL_HEADER)
                            .subHeader(constant.NOTIFICATION_TEMPLATE.REJECT_TO_SELL_SUBHEADER)
                            .content(constant.NOTIFICATION_TEMPLATE.REJECT_TO_SELL_CONTENT)
                            .build()
            );
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, message.ERROR_DENY_TO_SELL_TICKET, null);
        }
        return new ApiResponse<>(HttpStatus.OK, message.SUCCESS_DENY_TO_SELL_TICKET, null);
    }

    @Override
    public ApiResponse<List<TicketResponse>> getAllBoughtTicketsBySeller(Long sellerId) {
        List<TicketResponse> boughtTickets = ticketRepository.getAllBoughtTicketsBySeller(sellerId)
                .stream().map(ticketMapper::toResponse).toList();

        boughtTickets.forEach((item) -> {
            if (item.getBuyerId() != null) {
                item.setBuyer(
                        userMapper.toBuyerResponse(this.getUserById(item.getBuyerId()))
                );
            }
            item.setGenericTicketObject(
                    genericTicketMapper.toResponse(
                            genericTicketRepository.findById(item.getGenericTicketId()).get()
                    )
            );
        });

        return new ApiResponse<>(
                HttpStatus.OK, "", boughtTickets
        );
    }

    @Override
    public ApiResponse<List<TicketResponse>> getAllBoughtTicketsByBuyer(Long buyerId) {
        List<TicketResponse> tickets = ticketRepository.getAllBoughtTicketsByBuyer(buyerId)
                .stream().map(ticketMapper::toResponse).toList();
        tickets.forEach((item) -> {
            item.setGenericTicketObject(
                    genericTicketMapper.toResponse(
                            genericTicketRepository.findById(item.getGenericTicketId()).get()
                    )
            );
            // If rating is present, the generic ticket have been rated
            item.setIsRated(
                    ratingRepository.findByBuyerIdAndGenericTicketId(
                            buyerId, item.getGenericTicketId()
                    ).isPresent()
            );
        });
        return new ApiResponse<>(HttpStatus.OK, "", tickets);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(message.INVALID_BUYER)
        );
    }

    private OrderTicket getOrderTicketById(Long genericTicketId, Long buyerId, String orderNo) {
        return orderTicketRepository.findById(
                new OrderTicketID(genericTicketId, buyerId, orderNo.trim())
        ).orElseThrow(() -> new NotFoundException(message.ERROR_ORDER_TICKET_NOT_FOUND));
    }

    private String randomTransactionNo() {
        return UUID.randomUUID().toString().substring(1,8) + UUID.randomUUID().toString().substring(1,3);
    }
}
