package swp391.staffservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.User;
import swp391.staffservice.dto.response.SellerResponse;
import swp391.staffservice.utils.ImageUtil;

@Component
public class UserMapper {
    public SellerResponse toSellerResponse(User user){
        return SellerResponse.builder()
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
