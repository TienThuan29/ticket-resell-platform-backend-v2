package swp391.staffservice.service;

import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.dto.response.GenericTicketResponse;

import java.util.List;

public interface IStaffService {

    ApiResponse<?> verifyTicket(Long id);

    ApiResponse<List<GenericTicketResponse>> getAllGenericTicketNeedVerify(Long staffId);

    ApiResponse<?> markValidTicket(Long ticketId);

    ApiResponse<?> markInvalidTicket(Long ticketId, String note);
}
