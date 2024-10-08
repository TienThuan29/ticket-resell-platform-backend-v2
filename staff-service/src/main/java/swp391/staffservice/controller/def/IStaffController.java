package swp391.staffservice.controller.def;

import swp391.staffservice.dto.response.ApiResponse;

public interface IStaffController {

    ApiResponse<?> verifyTicket(Long id);


}
