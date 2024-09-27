package swp391.ticketservice.service.def;

import org.springframework.stereotype.Service;
import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.dto.response.ApiResponse;

import java.util.Date;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 */
@Service
public interface IGenericTicketService {

    ApiResponse<GenericTicketResponse> create(GenericTicketRequest genericTicketRequest);

    ApiResponse<?> updatePriceAndExpiredDate(Long id, Long price, Date date);

    ApiResponse<List<GenericTicketResponse>> getAll();

    ApiResponse<GenericTicketResponse> getById(Long id);

    ApiResponse<List<GenericTicketResponse>> getByCategory(Integer categoryId);

    ApiResponse<List<GenericTicketResponse>> getByEvent(Integer eventId);
}
