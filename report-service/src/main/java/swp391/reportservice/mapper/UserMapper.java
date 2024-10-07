package swp391.reportservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.User;
import swp391.reportservice.dto.response.UserResponse;
import swp391.reportservice.utils.ImageUtil;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .avatar(
                        user.getAvatar() == null ? null :  ImageUtil.decompressImage(user.getAvatar())
                )
                .email(user.getEmail())
                .build();
    }

}
