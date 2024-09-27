package swp391.ticketservice.mapper;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swp391.entity.User;
import swp391.entity.fixed.Role;
import swp391.userservice.dto.reponse.SellerResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.utils.ImageUtil;
import swp391.userservice.utils.RandomUtil;

@Component
public class UserMapper {
    public SellerResponse toSellerResponse(User user){
        return SellerResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .build();
    }

}
