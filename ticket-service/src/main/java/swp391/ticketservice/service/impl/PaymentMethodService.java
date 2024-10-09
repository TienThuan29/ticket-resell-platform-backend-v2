package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.PaymentMethodResponse;
import swp391.ticketservice.mapper.PaymentMethodMapper;
import swp391.ticketservice.repository.PaymentMethodRepository;
import swp391.ticketservice.service.def.IPaymentMethodService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodRepository paymentRepository;

    private final PaymentMethodMapper paymentMethodMapper;

    @Override
    public ApiResponse<List<PaymentMethodResponse>> getAllMethodNotDeleted() {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                paymentRepository.getAllMethodNotDeleted().stream()
                        .map(paymentMethodMapper::toResponse).toList()
        );
    }

}
