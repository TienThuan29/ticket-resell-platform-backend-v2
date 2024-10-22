package swp391.userservice.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.User;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.repository.UserRepository;
import swp391.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserReposrityTests {


    @Autowired
    private UserRepository userRepository;

    private User user;
    @BeforeEach
    void setUp() {
        List<User> testUser = userRepository.findAll();
        user = testUser.get(3);
    }



    @Test
    void testFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("huynek", foundUser.get().getUsername());
    }

    @Test
    void testFindEnableAccount() {
        Optional<User> foundUser = userRepository.findEnableAccount(user.getUsername());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertTrue(foundUser.get().getIsEnable());
    }

    @Test
    void testFindByCustomerCode() {
        Optional<User> foundUser = userRepository.findByCustomerCode(user.getCustomerCode());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("73ccc16c7", foundUser.get().getCustomerCode());
    }

    @Test
    void testFindByEmail() {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("huhu@gmail.com", foundUser.get().getEmail());
    }

    @Test
    void testUpdateIsSellerById() {
        userRepository.updateIsSellerById(user.getId());
        Optional<User> updatedUser = userRepository.findById(user.getId());
        Assertions.assertTrue(updatedUser.isPresent());
        Assertions.assertTrue(updatedUser.get().getIsSeller());
    }
}
