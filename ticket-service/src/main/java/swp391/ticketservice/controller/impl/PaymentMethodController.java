package swp391.ticketservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.ticketservice.controller.def.IPaymentMethodController;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.PaymentMethodResponse;
import swp391.ticketservice.service.def.IPaymentMethodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class PaymentMethodController implements IPaymentMethodController {

    private final IPaymentMethodService paymentMethodService;

    @Override
    @GetMapping("/get/not-deleted-payment-method")
    public ApiResponse<List<PaymentMethodResponse>> getAllMethodNotDeleted() {
        return paymentMethodService.getAllMethodNotDeleted();
    }
}
