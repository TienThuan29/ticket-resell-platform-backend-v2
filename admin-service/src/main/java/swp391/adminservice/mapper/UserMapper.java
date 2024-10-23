package swp391.adminservice.mapper;

import org.springframework.stereotype.Component;
import swp391.adminservice.dto.response.UserResponse;
import swp391.entity.User;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .isEnable(user.getIsEnable())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .balance(user.getBalance())
                .isSeller(user.getIsSeller())
                .revenue(user.getRevenue())
                .build();
    }
}
