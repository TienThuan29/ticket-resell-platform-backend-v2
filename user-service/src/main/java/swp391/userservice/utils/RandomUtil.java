package swp391.userservice.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swp391.userservice.repository.UserRepository;

import java.util.UUID;

@Component("userServiceRandomUtil")
public class RandomUtil {

    @Autowired
    private UserRepository userRepository;

    public String randomCustomerCode() {
        String customerCode;
        do {
            customerCode = UUID.randomUUID().toString().substring(1,8) + UUID.randomUUID().toString().substring(1,3);
        } while (isExistCustomerCode((customerCode)));
        return customerCode;
    }

    private boolean isExistCustomerCode(String customerCode) {
        return userRepository.findByCustomerCode(customerCode).isPresent();
    }

}
