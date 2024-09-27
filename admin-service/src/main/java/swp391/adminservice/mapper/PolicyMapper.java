package swp391.adminservice.mapper;

import org.springframework.stereotype.Component;
import swp391.adminservice.dto.response.PolicyResponse;
import swp391.entity.Policy;
import java.util.Arrays;

@Component("adminServicePolicyMapper")
public class PolicyMapper {

    public PolicyResponse toPolicyResponse(Policy policy) {
        return PolicyResponse.builder()
                .id(policy.getId())
                .content(
                        Arrays.asList(policy.getContent().split("\n"))
                )
                .fee(policy.getFee())
                .typePolicy(policy.getTypePolicy())
                .build();
    }

}

