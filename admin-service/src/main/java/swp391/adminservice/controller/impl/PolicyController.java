package swp391.adminservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.adminservice.controller.def.IPolicyController;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.PolicyResponse;
import swp391.adminservice.service.impl.PolicyService;

@RestController
@RequestMapping("/api/policy")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PolicyController implements IPolicyController {

    private final PolicyService policyService;

    @Override
    @GetMapping("/get/selling")
    public ApiResponse<PolicyResponse> getSellingPolicy() {
        return policyService.getSellingPolicy();
    }

    @Override
    @GetMapping("/get/buying")
    public ApiResponse<PolicyResponse> getBuyingPolicy() {
        return policyService.getBuyingPolicy();
    }

    @Override
    @GetMapping("/get/general")
    public ApiResponse<PolicyResponse> getGeneralPolicy() {
        return policyService.getGeneralPolicy();
    }

}
