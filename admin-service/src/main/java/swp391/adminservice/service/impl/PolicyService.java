package swp391.adminservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;
import swp391.adminservice.mapper.PolicyMapper;
import swp391.adminservice.repository.PolicyRepository;
import swp391.adminservice.service.def.IPolicyService;

@Service
@RequiredArgsConstructor
public class PolicyService implements IPolicyService {

    private final PolicyRepository policyRepository;

    private final PolicyMapper policyMapper;

    @Override
    public ApiResponse<PolicyResponse> getSellingPolicy() {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                policyMapper.toPolicyResponse(policyRepository.findById(3).get())
        );
    }

    @Override
    public ApiResponse<PolicyResponse> getBuyingPolicy() {
        return new ApiResponse<>(HttpStatus.OK,
                "", policyMapper.toPolicyResponse(policyRepository.findById(5).get()));
    }

    @Override
    public ApiResponse<PolicyResponse> getGeneralPolicy() {
        return new ApiResponse<>(HttpStatus.OK,
                "", policyMapper.toPolicyResponse(policyRepository.findById(6).get()));
    }


}
