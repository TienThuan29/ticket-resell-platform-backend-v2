package swp391.userservice.Service;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.UpdateInfoRequest;
import swp391.userservice.mapper.UserMapper;
import swp391.userservice.repository.UserRepository;
import swp391.userservice.service.UserService;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private UserDTO userDto;

    private  UpdateInfoRequest userUpdateInfoRequest;
    @BeforeEach
    void setup() {
        var users = userRepository.findAll();
        userDto = userMapper.toUserDTO(users.get(2));
        userUpdateInfoRequest = UpdateInfoRequest.builder().
                firstname("marx").
                lastname("Arex").
                phone("0123456789").
                email("hehe@gmail.com")
                .build();
    }


    @Test
    @Description("This test verifies that a user can be retrieved by ID")
    @Epic("User Management")
    @Feature("Get User by ID")
    @Severity(SeverityLevel.CRITICAL)
    void testGetByID() {
        ApiResponse<UserDTO> foundUser = userService.getById(3L);
        Assertions.assertEquals(userDto.getEmail(), foundUser.getBody().getObject().getEmail());
    }


//    @Test
//    void testUpdateIsSeller() {
//        userService.updateIsSeller(3L);
//        ApiResponse<UserDTO> updateSeller = userService.getById(3L);
//        Assertions.assertTrue(updateSeller.getBody().getObject().getIsSeller());
//    }
//
//    @Test
//    void testChangePassword() {
//        userService.changePassword(3L, "123456", "654321");
//        var changePassUser = userRepository.findById(3L);
//        Assertions.assertTrue(BCrypt.checkpw("654321", changePassUser.get().getPassword()));
//    }
//
//    @Test
//    void testUpdateUser() {
//        userService.update(1L, userUpdateInfoRequest);
//        ApiResponse<UserDTO> checkUpdate = userService.getById(1L);
//        Assertions.assertEquals(userUpdateInfoRequest.getEmail(), checkUpdate.getBody().getObject().getEmail());
//    }
}
