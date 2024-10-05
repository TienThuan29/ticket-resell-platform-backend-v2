package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;

public interface IAdminService {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

}
