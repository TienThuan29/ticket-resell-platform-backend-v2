package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.PolicyUpdatingRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;

import java.util.List;

public interface IPolicyService {

    ApiResponse<PolicyResponse> getSellingPolicy();

    ApiResponse<PolicyResponse> getBuyingPolicy();

    ApiResponse<PolicyResponse> getGeneralPolicy();

    ApiResponse<List<PolicyResponse>> getAllPolicy();

    ApiResponse<?> updatePolicy(Integer id, PolicyUpdatingRequest policyUpdatingRequest);
}
