package swp391.userservice.mapper;

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

    @Autowired
    private RandomUtil randomUtil;

    public User registerRequestToEntity(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getUsername())
                .password(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt()))
                .email(registerRequest.getEmail())
                .customerCode(randomUtil.randomCustomerCode())
                .balance(0L)
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .isEnable(Boolean.FALSE)
                .isSeller(Boolean.FALSE)
                .revenue(0L)
                .roleCode(Role.USER)
                .build();
    }

    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .isEnable(user.getIsEnable())
                .roleCode(user.getRoleCode())
                .balance(user.getBalance())
                .phone(user.getPhone())
                .revenue(user.getRevenue())
                .customerCode(user.getCustomerCode())
                .isSeller(user.getIsSeller())
                .avatar(
                        user.getAvatar() == null ? null :  ImageUtil.decompressImage(user.getAvatar())
                )
                .build();
    }

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
