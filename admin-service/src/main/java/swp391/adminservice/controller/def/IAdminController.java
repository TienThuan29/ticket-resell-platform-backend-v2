package swp391.adminservice.controller.def;

import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.entity.Policy;

public interface IAdminController {

    ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest);

}
