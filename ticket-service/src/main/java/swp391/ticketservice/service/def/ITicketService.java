package swp391.ticketservice.service.def;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;
import swp391.ticketservice.dto.request.AcceptOrDenySellingRequest;
import swp391.ticketservice.dto.request.TicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.TicketResponse;

import java.io.IOException;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 */
@Service
public interface ITicketService {
    ApiResponse<List<TicketResponse>> getAll();

    ApiResponse<TicketResponse> getById(Long id);

    ApiResponse<TicketResponse> create(TicketRequest ticketRequest, MultipartFile file) throws IOException;

    ApiResponse<?> markBought(Long id);

    ApiResponse<?> markDeliveredPaperTicket(Long ticketId);

    ApiResponse<?> markStaffCheck(Long id, Long staffId);

    ApiResponse<?> updateProcess(Long id, String process);

    ApiResponse<List<TicketResponse>> getTicketsByProcess(GeneralProcess process);

    ApiResponse<List<TicketResponse>> getGenericTicketWithTicketsOfSeller(Long sellerId);

    ApiResponse<?> acceptToSellTicket(AcceptOrDenySellingRequest request);

    ApiResponse<?> denyToSellTicket(AcceptOrDenySellingRequest request);

    ApiResponse<List<TicketResponse>> getAllBoughtTicketsBySeller(Long sellerId);

    ApiResponse<List<TicketResponse>> getAllBoughtTicketsByBuyer(Long buyerId);
}
