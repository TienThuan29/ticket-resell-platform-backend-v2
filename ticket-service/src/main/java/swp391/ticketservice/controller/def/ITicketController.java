package swp391.ticketservice.controller.def;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
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
public interface ITicketController {

    ApiResponse<List<TicketResponse>> getAll();

    ApiResponse<TicketResponse> getById(Long id);

    ApiResponse<TicketResponse> create(TicketRequest ticketRequest, MultipartFile file) throws IOException;

    ApiResponse<?> markBought(Long id);

    ApiResponse<?> markStaffCheck(Long id, Long staffId);

    ApiResponse<?> updateProcess(Long id, String process);

    ApiResponse<List<TicketResponse>> getTicketByProcess(GeneralProcess process);

    ApiResponse<?> acceptToSellTicket(AcceptOrDenySellingRequest request);

    ApiResponse<List<TicketResponse>> getAllBoughtTicketsBySeller(Long sellerId);
}
