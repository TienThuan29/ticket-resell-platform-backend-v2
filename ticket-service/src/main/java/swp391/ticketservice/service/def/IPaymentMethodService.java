package swp391.ticketservice.service.def;

import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.PaymentMethodResponse;

import java.util.List;

public interface IPaymentMethodService {

    ApiResponse<List<PaymentMethodResponse>> getAllMethodNotDeleted();

}
