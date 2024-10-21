package swp391.adminservice.controller.def;

import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;

public interface IPolicyController {

    ApiResponse<PolicyResponse> getSellingPolicy();

    ApiResponse<PolicyResponse> getBuyingPolicy();

    ApiResponse<PolicyResponse> getGeneralPolicy();
}
