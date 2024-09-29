package swp391.ticketservice.controller.def;

import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import java.util.Date;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 */
public interface IGenericTicketController {

    ApiResponse<GenericTicketResponse> createGenericTicket(GenericTicketRequest genericTicketRequest);

    ApiResponse<GenericTicketResponse> updateGenericTicket(Long id, GenericTicketRequest genericTicketRequest);

    ApiResponse<?> updatePriceAndExpiredDate(Long id, Long price, Date date);

    ApiResponse<List<GenericTicketResponse>> getAll();

    ApiResponse<GenericTicketResponse> getById(Long id);

    ApiResponse<List<GenericTicketResponse>> getByCategory(Integer categoryId);

    ApiResponse<List<GenericTicketResponse>> getByEvent(Integer eventId);

    ApiResponse<Integer> getTotalTicketsInGenericTicket(Long genericTicketId);
}
