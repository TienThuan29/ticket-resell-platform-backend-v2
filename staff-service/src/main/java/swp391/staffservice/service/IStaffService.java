package swp391.staffservice.service;

import swp391.staffservice.dto.response.ApiResponse;

public interface IStaffService {

    ApiResponse<?> verifyTicket(Long id);

}
