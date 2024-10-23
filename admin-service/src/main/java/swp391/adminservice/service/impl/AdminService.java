package swp391.adminservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.dto.response.TransactionResponse;
import swp391.adminservice.dto.response.UserResponse;
import swp391.adminservice.mapper.StaffMapper;
import swp391.adminservice.mapper.TransactionMapper;
import swp391.adminservice.mapper.UserMapper;
import swp391.adminservice.repository.StaffRepository;
import swp391.adminservice.repository.TransactionRepository;
import swp391.adminservice.repository.UserRepository;
import swp391.adminservice.service.def.IAdminService;
import swp391.entity.Staff;
import swp391.entity.Transaction;
import swp391.entity.User;
import swp391.entity.fixed.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final TransactionMapper transactionMapper;

    private final StaffRepository staffRepository;

    private final StaffMapper staffMapper;

    private final MessageConfiguration messageConfig;

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public ApiResponse<StaffDTO> registerStaff(RegisterRequest registerRequest) {
        var staff = staffRepository.findByUsername(registerRequest.getUsername());

        if (staff.isPresent())
            return new ApiResponse<>(HttpStatus.CONFLICT, messageConfig.ERROR_EXIST_USERNAME, null);

        if(this.isExistEmail(registerRequest.getEmail()))
            return new ApiResponse<>(HttpStatus.CONFLICT, messageConfig.ERROR_EXIST_EMAIL, null);

        Staff savedStaff = staffRepository.save(staffMapper.toEntity(registerRequest));

        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_REGISTER_STAFF, staffMapper.toDTO(savedStaff));
    }

    @Override
    public ApiResponse<List<StaffDTO>> getListStaffs() {
        List<Staff> listStaff = staffRepository.getAll(Role.STAFF);
        List<StaffDTO> staffDTOList = new ArrayList<>();
        listStaff.forEach(staff -> {
            StaffDTO item = staffMapper.toDTO(staff);
            staffDTOList.add(item);
        });

        return new ApiResponse<>(HttpStatus.OK,"list Staffs",staffDTOList);
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getListTransactions() {
        List<Transaction> listTransactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponseList = listTransactions.stream()
                .map(transactionMapper::toTransactionResponse).collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK,"List transaction",transactionResponseList);
    }

    @Override
    public ApiResponse<List<UserResponse>> getListUsers() {
        List<User> listUsers = userRepository.getAllUsers();
        List<UserResponse> userResponseList = new ArrayList<>();
        listUsers.stream().map(userMapper::toUserResponse).forEach(item -> userResponseList.add(item));
        return new ApiResponse<>(HttpStatus.OK, "List users",userResponseList);
    }

    private boolean isExistEmail(String email) {
        var user = staffRepository.findByEmail(email);
        return user.isPresent();
    }
}
