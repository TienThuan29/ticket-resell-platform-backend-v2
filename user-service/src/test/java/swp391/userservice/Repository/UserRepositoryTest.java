package swp391.userservice.Repository;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.User;
import swp391.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<User> testUser;
    private User user;

    @BeforeEach
    void setUp() {
        testUser = userRepository.findAll();
        user = testUser.get(3);
    }

    @Test
    @Description("Test to find a user by username")
    @Step("Executing testFindByUsername")
    @Story("User should be found by username")
    void testFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("huynek", foundUser.get().getUsername());
    }

    @Test
    @Description("Test to find an enabled account")
    @Step("Executing testFindEnableAccount")
    @Story("User account should be enabled")
    void testFindEnableAccount() {
        Optional<User> foundUser = userRepository.findEnableAccount(user.getUsername());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertTrue(foundUser.get().getIsEnable());
    }

    @Test
    @Description("Test to find a user by customer code")
    @Step("Executing testFindByCustomerCode")
    @Story("User should be found by customer code")
    void testFindByCustomerCode() {
        Optional<User> foundUser = userRepository.findByCustomerCode(user.getCustomerCode());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("73ccc16c7", foundUser.get().getCustomerCode());
    }

    @Test
    @Description("Test to find a user by email")
    @Step("Executing testFindByEmail")
    @Story("User should be found by email")
    void testFindByEmail() {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("huhu@gmail.com", foundUser.get().getEmail());
    }

    @Test
    @Description("Test to update user seller status by ID")
    @Step("Executing testUpdateIsSellerById")
    @Story("User seller status should be updated")
    void testUpdateIsSellerById() {
        userRepository.updateIsSellerById(user.getId());
        Optional<User> updatedUser = userRepository.findById(user.getId());
        Assertions.assertTrue(updatedUser.isPresent());
        Assertions.assertTrue(updatedUser.get().getIsSeller());
    }
}
