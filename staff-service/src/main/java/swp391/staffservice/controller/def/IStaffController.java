package swp391.staffservice.controller.def;

import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.dto.response.GenericTicketResponse;

import java.util.List;

public interface IStaffController {

    ApiResponse<?> verifyTicket(Long id);

    ApiResponse<List<GenericTicketResponse>> getAllGenericTicketNeedVerify(Long staffId);

    ApiResponse<?> markValidTicket(Long ticketId);

    ApiResponse<?> markInvalidTicket(Long ticketId, String note);

}
