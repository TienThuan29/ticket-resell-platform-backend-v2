package swp391.ticketservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.User;
import swp391.ticketservice.dto.response.BuyerResponse;
import swp391.ticketservice.dto.response.SellerResponse;
import swp391.ticketservice.utils.ImageUtil;

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

    public BuyerResponse toBuyerResponse(User user){
        return BuyerResponse.builder()
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
<<<<<<< HEAD
=======

>>>>>>> e020ee652e212f6555e91cafe6f5417ec437c4de
}
