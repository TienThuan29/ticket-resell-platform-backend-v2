package swp391.staffservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.staffservice.controller.def.IStaffController;
import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.service.IStaffService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staffs")
public class StaffController implements IStaffController {

    private final IStaffService staffService;

    @Override
    public ApiResponse<?> verifyTicket(Long id) {

        return null;
    }

}
