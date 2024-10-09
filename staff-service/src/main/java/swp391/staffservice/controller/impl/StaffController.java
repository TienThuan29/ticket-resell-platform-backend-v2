package swp391.staffservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.staffservice.controller.def.IStaffController;
import swp391.staffservice.dto.response.ApiResponse;
import swp391.staffservice.dto.response.GenericTicketResponse;
import swp391.staffservice.service.IStaffService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staffs")
public class StaffController implements IStaffController {

    private final IStaffService staffService;

    @Override
    public ApiResponse<?> verifyTicket(Long id) {

        return null;
    }

    @Override
    @GetMapping("/get-generic-ticket/need-verify/{id}")
    public ApiResponse<List<GenericTicketResponse>> getAllGenericTicketNeedVerify(@PathVariable("id") Long staffId) {
        return staffService.getAllGenericTicketNeedVerify(staffId);
    }

}
