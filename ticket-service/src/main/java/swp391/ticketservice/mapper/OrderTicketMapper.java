package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.GenericTicket;
import swp391.entity.OrderTicket;
import swp391.entity.embedable.OrderTicketID;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.OrderTicketRequest;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.PaymentMethodRepository;
import swp391.ticketservice.repository.UserRepository;

@Component("ticketServiceOrderTicketMapper")
@RequiredArgsConstructor
public class OrderTicketMapper {

    private final MessageConfiguration messageConfig;

    private final UserRepository userRepo;

    private final GenericTicketRepository genericTicketRepo;

    private final PaymentMethodRepository paymentMethodRepo;

    public OrderTicket toEntity(OrderTicketRequest orderTicketRequest) {
        var buyer = userRepo.findById(orderTicketRequest.getBuyerId()).orElseThrow(
                () -> new NotFoundException(
                        messageConfig.INVALID_BUYER+ ": "+orderTicketRequest.getBuyerId()
                )
        );
        var geneticTicket = genericTicketRepo.findById(orderTicketRequest.getGenericTicketId()).orElseThrow(
                () -> new NotFoundException(
                        messageConfig.INVALID_GENERICTICKET+": "+orderTicketRequest.getGenericTicketId()
                )
        );
        var paymentMethod = paymentMethodRepo.findById(orderTicketRequest.getPaymentMethodId()).orElseThrow(
                () -> new NotFoundException(
                        messageConfig.INVALID_PAYMENT_METHOD_ID+": "+orderTicketRequest.getPaymentMethodId()
                )
        );
        return OrderTicket.builder()
                .buyer(buyer)
                .genericTicket(geneticTicket)
                .quantity(orderTicketRequest.getQuantity())
                .paymentMethod(paymentMethod)
                .isAccepted(Boolean.FALSE)
                .totalPrice(orderTicketRequest.getTotalPrice())
                .note("")
                .build();
    }

}
