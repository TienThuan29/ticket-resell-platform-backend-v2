package swp391.userservice.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swp391.userservice.repository.UserRepository;
import swp391.userservice.repository.VerificationUserRepository;

import java.util.Random;
import java.util.UUID;

@Component("userServiceRandomUtil")
public class RandomUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationUserRepository verificationUserRepository;

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

    public String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        do{
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(characters.length());
                sb.append(characters.charAt(randomIndex));
            }
        }while (verificationUserRepository.findByVerificationCode(sb.toString()).isPresent());

        return sb.toString();
    }
}
