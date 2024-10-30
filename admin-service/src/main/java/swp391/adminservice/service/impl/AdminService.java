package swp391.adminservice.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.request.UpdateStaffRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.dto.response.TransactionResponse;
import swp391.adminservice.dto.response.UserResponse;
import swp391.adminservice.dto.response.*;
import swp391.adminservice.mapper.StaffMapper;
import swp391.adminservice.mapper.TransactionMapper;
import swp391.adminservice.mapper.UserMapper;
import swp391.adminservice.repository.*;
import swp391.adminservice.service.def.IAdminService;
import swp391.entity.*;
import swp391.entity.fixed.GeneralProcess;
import swp391.entity.fixed.Role;
import swp391.entity.fixed.TransactionType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    private final EntityManager entityManager;

    private final PolicyRepository policyRepository;

    private final GenericTicketRepository genericTicketRepo;

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
    public ApiResponse<StaffDTO> updateStaff(Long staffId, UpdateStaffRequest updateStaffRequest) {
        StaffDTO staffDTO = null;
        try {
            var staff = staffRepository.findById(staffId).orElseThrow(null);
            staff.setFirstname(updateStaffRequest.getFirstname());
            staff.setLastname(updateStaffRequest.getLastname());
            staff.setEmail(updateStaffRequest.getEmail());
            staff.setPhone(updateStaffRequest.getPhone());
            staffDTO = staffMapper.toDTO(staffRepository.save(staff));
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_UPDATE_STAFF, staffDTO);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_UPDATE_STAFF, staffDTO);
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
    public ApiResponse<?> enableUserAccount(Long userId) {
        try {
            var account = userRepository.findById(userId).orElseThrow(null);
            account.setIsEnable(Boolean.TRUE);
            userRepository.save(account);
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_ENABLE_ACCOUNT, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_ENABLE_ACCOUNT, null);
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
    public ApiResponse<?> enableStaffAccount(Long staffId) {
        try {
            var account = staffRepository.findById(staffId).orElseThrow(null);
            account.setIsEnable(Boolean.TRUE);
            staffRepository.save(account);
        }
        catch (Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_ENABLE_ACCOUNT, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_ENABLE_ACCOUNT, null);
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
        var genericTickets = genericTicketRepo.findAll().stream()
                .filter(genericTicket ->
                        isExpiredMoreThanThreeDays(genericTicket.getExpiredDateTime()))
                .toList();
//        var genericTickets = genericTicketRepo.findAll();
        Integer count = ticketRepository.countBoughtTickets(genericTickets);
        return new ApiResponse<>(HttpStatus.OK, "count bought tickets",count);
    }

    @Override
    public ApiResponse<Integer> countSellingTickets() {
        Integer count = ticketRepository.countSellingTickets(GeneralProcess.SUCCESS);
        return new ApiResponse<>(HttpStatus.OK, "count selling tickets",count);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ApiResponse<List<EventRevenueResponse>> getEventsRevenue() {
        List<Object[]> results = entityManager.createNativeQuery("EXEC SelectEventRevenue").getResultList();

        Policy sellingPolicy = policyRepository.findById(3).get();

        // Transform each row (Object[]) into an EventRevenueResponse object
        List<EventRevenueResponse> eventRevenueResponses = results.stream()
                .map(row -> new EventRevenueResponse(
                        (String) row[0],           // name
                        ((Integer) row[1]),        // boughtQuantity
                        (Date) row[2],             // startDate
                        (Double) row[3]            // revenueEvent
                ))
                .collect(Collectors.toList());
        eventRevenueResponses.stream()
                .forEach(e -> e.setRevenueEvent
                        (e.getRevenueEvent()*sellingPolicy.getFee()/100));
        return new ApiResponse<>(HttpStatus.OK, "", eventRevenueResponses);
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

    private boolean isExpiredMoreThanThreeDays(Date expiredDate){
        Date presentDate = getPresentDate();
        if(expiredDate.before(presentDate)){
            long periodMilli= Math.abs(expiredDate.getTime()-presentDate.getTime());
            // 259200000 milliseconds = 3 days
            return periodMilli > 259200000;
        }
        return false;
    }

    private Date getPresentDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }
}
