package swp391.adminservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.adminservice.configuration.MessageConfiguration;
import swp391.adminservice.dto.request.RegisterRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.StaffDTO;
import swp391.adminservice.mapper.StaffMapper;
import swp391.adminservice.repository.StaffRepository;
import swp391.adminservice.service.def.IAdminService;
import swp391.entity.Staff;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final StaffRepository staffRepository;

    private final StaffMapper staffMapper;

    private final MessageConfiguration messageConfig;

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

    private boolean isExistEmail(String email) {
        var user = staffRepository.findByEmail(email);
        return user.isPresent();
    }
}
