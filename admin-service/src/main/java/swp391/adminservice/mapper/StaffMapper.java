package swp391.adminservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.utils.ImageUtil;
import swp391.adminservice.utils.RandomUtil;
import swp391.entity.Staff;
import swp391.entity.fixed.Role;

@RequiredArgsConstructor
@Component("adminServiceStaffMapper")
public class StaffMapper {

    private final RandomUtil randomUtil;

    private final PasswordEncoder passwordEncoder;


    public Staff toEntity(RegisterRequest registerRequest) {
        return Staff.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .staffCode(randomUtil.randomStaffCode())
                .balance(0L)
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .isEnable(Boolean.TRUE)
                .revenue(0L)
                .roleCode(Role.STAFF)
                .build();
    }

    public StaffDTO toDTO(Staff staff) {
        return StaffDTO.builder()
                .id(staff.getId())
                .username(staff.getUsername())
                .firstname(staff.getFirstname())
                .lastname(staff.getLastname())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .isEnable(staff.isEnabled())
                .roleCode(staff.getRoleCode())
                .balance(staff.getBalance())
                .revenue(staff.getRevenue())
                .staffCode(staff.getStaffCode())
                .avatar(
                        staff.getAvatar() == null ? null :  ImageUtil.decompressImage(staff.getAvatar())
                )
                .build();
    }

    // Create ADMIN account -> Open this function and comment above function
    /*
    public Staff toEntity(RegisterRequest registerRequest) {
        return Staff.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .staffCode(randomUtil.randomStaffCode())
                .balance(0L)
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .isEnable(Boolean.TRUE)
                .revenue(0L)
                .roleCode(Role.ADMIN)
                .build();
    }
    */
}
