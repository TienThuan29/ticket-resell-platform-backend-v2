package swp391.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.User;
import swp391.entity.fixed.Role;
import swp391.userservice.configuration.MessageConfiguration;
import swp391.userservice.dto.reponse.ApiResponse;
import swp391.userservice.dto.reponse.UserDTO;
import swp391.userservice.dto.request.AuthenticationRequest;
import swp391.userservice.dto.request.RegisterRequest;
import swp391.userservice.dto.request.UpdateInfoRequest;
import swp391.userservice.exception.def.NotFoundException;
import swp391.userservice.mapper.UserMapper;
import swp391.userservice.repository.UserRepository;
import swp391.userservice.utils.ImageUtil;

import java.io.IOException;
import java.util.UUID;

/**
 * Author: Nguyen Tien Thuan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final MessageConfiguration messageConfig;

    private final UserMapper userMapper;

    @Override
    public ApiResponse<UserDTO> getById(Long id) {
        var user = userRepository.findById(id);
        return new ApiResponse<>(HttpStatus.OK, "", userMapper.toUserDTO(user.get()));
    }

    @Override
    public ApiResponse<?> updateAvatar(Long id, MultipartFile file) {
        if (file.isEmpty())
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_AVATAR_FAIL, null);

        var user = userRepository.findById(id).get();
        try {
            user.setAvatar(ImageUtil.compressImage(file.getBytes()));
            userRepository.save(user);
        }
        catch (IOException exception) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_AVATAR_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.MESSAGE_UPDATE_AVATAR_SUCCESS, null);
    }

    @Override
    public ApiResponse<?> updateIsSeller(Long id) {
        try {
            userRepository.updateIsSellerById(id);
        }
        catch (Exception exception) {
            log.info("Exception: "+exception.getMessage());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.ERROR_UPDATE_ISSELLER_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.MESSAGE_UPDATE_ISSELLER_SUCCESS, null);
    }


    @Override
    public ApiResponse<UserDTO> authenticate(AuthenticationRequest authRequest) {
        var user = userRepository.findByUsername(authRequest.getUsername());

        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(
                HttpStatus.FORBIDDEN,
                messageConfig.ERROR_INVALID_USERNAME_PASSWORD, null
        );

        if (user.isPresent()) {
            var matchedPassword = BCrypt.checkpw(authRequest.getPassword(), user.get().getPassword());
            if (matchedPassword)
                apiResponse = new ApiResponse<>(HttpStatus.OK, "", userMapper.toUserDTO(user.get()));
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        var user = userRepository.findByUsername(registerRequest.getUsername());
        if (user.isPresent())
            return new ApiResponse<>(HttpStatus.CONFLICT, messageConfig.ERROR_USERNAME_EXIST, null);
        userRepository.save(userMapper.registerRequestToEntity(registerRequest));
        return new ApiResponse<>(HttpStatus.CREATED, messageConfig.ERROR_REGISTER_SUCCESS, null);
    }

    @Override
    public ApiResponse<UserDTO> update(Long id, UpdateInfoRequest updateInfoRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageConfig.ERROR_NOT_FOUND_USERID)
        );
        user.setFirstname(updateInfoRequest.getFirstname());
        user.setLastname(updateInfoRequest.getLastname());
        user.setPhone(
                updateInfoRequest.getPhone().trim().isEmpty() ? null : updateInfoRequest.getPhone()
        );
        user.setEmail(updateInfoRequest.getEmail());
        return new ApiResponse<>(
                HttpStatus.OK,
                messageConfig.MESSAGE_UPDATE_USER_SUCCESS,
                userMapper.toUserDTO(userRepository.save(user))
        );
    }


}
