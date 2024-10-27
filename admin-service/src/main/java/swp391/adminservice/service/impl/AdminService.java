package swp391.adminservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.*;
import swp391.adminservice.mapper.StaffMapper;
import swp391.adminservice.mapper.TransactionMapper;
import swp391.adminservice.mapper.UserMapper;
import swp391.adminservice.repository.*;
import swp391.adminservice.service.def.IAdminService;
import swp391.entity.Staff;
import swp391.entity.Transaction;
import swp391.entity.User;
import swp391.entity.fixed.GeneralProcess;
import swp391.entity.fixed.Role;
import swp391.entity.fixed.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    private final TicketRepository ticketRepository;

    private final EventRepository eventRepository;

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

        return new ApiResponse<>(HttpStatus.OK,"",staffDTOList);
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getListTransactions() {
        List<Transaction> listTransactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponseList = listTransactions.stream()
                .map(transactionMapper::toTransactionResponse).collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK,"",transactionResponseList);
    }

    @Override
    public ApiResponse<List<UserResponse>> getListUsers() {
        List<User> listUsers = userRepository.getAllUsers();
        List<UserResponse> userResponseList = new ArrayList<>();
        listUsers.stream().map(userMapper::toUserResponse).forEach(item -> userResponseList.add(item));
        return new ApiResponse<>(HttpStatus.OK, "",userResponseList);
    }

    @Override
    public ApiResponse<?> disableUserAccount(Long userId) {
        try {
            var account = userRepository.findById(userId).orElseThrow(null);
            account.setIsEnable(Boolean.FALSE);
            userRepository.save(account);
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_DISABLE_ACCOUNT, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_DISABLE_ACCOUNT, null);
    }

    @Override
    public ApiResponse<?> disableStaffAccount(Long staffId) {
        try {
            var account = staffRepository.findById(staffId).orElseThrow(null);
            account.setIsEnable(Boolean.FALSE);
            staffRepository.save(account);
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_DISABLE_ACCOUNT, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_DISABLE_ACCOUNT, null);
    }

    @Override
    public ApiResponse<Long> getSumAmountOfDepositTransaction() {
        Long sum = transactionRepository.sumTheAmountByTransactionType(TransactionType.DEPOSIT);
        return new ApiResponse<>(HttpStatus.OK,"Sum of Deposit", sum);
    }

    @Override
    public ApiResponse<Long> getSumAmountOfWithdrawalTransaction() {
        Long sum = transactionRepository.sumTheAmountByTransactionType(TransactionType.WITHDRAWAL);
        return new ApiResponse<>(HttpStatus.OK,"Sum of Withdrawal",sum);
    }

    @Override
    public ApiResponse<Integer> countBoughtTickets() {
        Integer count = ticketRepository.countBoughtTickets();
        return new ApiResponse<>(HttpStatus.OK, "count bought tickets",count);
    }

    @Override
    public ApiResponse<Integer> countSellingTickets() {
        Integer count = ticketRepository.countSellingTickets(GeneralProcess.SELLING);
        return new ApiResponse<>(HttpStatus.OK, "count selling tickets",count);
    }

    @Override
    public ApiResponse<List<EventRevenueResponse>> getEventsRevenue() {
        return new ApiResponse<>(HttpStatus.OK, "", eventRepository.getEventRevenue());
    }

    @Override
    public ApiResponse<Long> getRevenue() {
        Long revenue = staffRepository.getAll(Role.ADMIN).get(0).getRevenue();
        return new ApiResponse<>(HttpStatus.OK, "", revenue);
    }

    private boolean isExistEmail(String email) {
        var user = staffRepository.findByEmail(email);
        return user.isPresent();
    }
}
