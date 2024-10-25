package swp391.adminservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.dto.request.PolicyUpdatingRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;
import swp391.adminservice.mapper.PolicyMapper;
import swp391.adminservice.repository.PolicyRepository;
import swp391.adminservice.service.def.IPolicyService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyService implements IPolicyService {

    private final PolicyRepository policyRepository;

    private final PolicyMapper policyMapper;

    private final MessageConfiguration messageConfig;

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

    @Override
    public ApiResponse<List<PolicyResponse>> getAllPolicy() {
        return new ApiResponse<>(HttpStatus.OK, "",
                policyRepository.findAll().stream().map(policyMapper::toPolicyResponse).toList()
        );
    }

    @Override
    public ApiResponse<?> updatePolicy(Integer id, PolicyUpdatingRequest policyUpdatingRequest) {
        try {
            var policy = policyRepository.findById(id).orElseThrow(null);
            policy.setFee(policyUpdatingRequest.getFee());
            policy.setContent(policyUpdatingRequest.getContent());
            policyRepository.save(policy);
        }
        catch (NullPointerException ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_POLICY, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_UPDATE_POLICY, null);
    }


}
