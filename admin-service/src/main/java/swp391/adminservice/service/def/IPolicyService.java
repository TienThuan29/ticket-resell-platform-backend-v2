package swp391.adminservice.service.def;

import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;

public interface IPolicyService {

    ApiResponse<PolicyResponse> getSellingPolicy();

}
