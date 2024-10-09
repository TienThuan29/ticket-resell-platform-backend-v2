package swp391.ticketservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.PaymentMethod;
import swp391.ticketservice.dto.response.PaymentMethodResponse;

@Component("ticketServicePaymentMethodMapper")
public class PaymentMethodMapper {

    public PaymentMethodResponse toResponse(PaymentMethod paymentMethod) {
        return PaymentMethodResponse.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .isDeleted(paymentMethod.isDeleted())
                .build();
    }

}
