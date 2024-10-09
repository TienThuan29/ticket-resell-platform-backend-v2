package swp391.ticketservice.controller.def;

import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.PaymentMethodResponse;

import java.util.List;

public interface IPaymentMethodController {

    ApiResponse<List<PaymentMethodResponse>> getAllMethodNotDeleted();

}
