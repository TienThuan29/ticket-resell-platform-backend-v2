package swp391.reportservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.reportservice.controller.def.IReportTypeController;
import swp391.reportservice.dto.response.ApiResponse;
import swp391.reportservice.service.def.IReportTypeService;

@RestController
@RequestMapping("/api/report-type")
@RequiredArgsConstructor
public class ReportTypeController implements IReportTypeController {

    private final IReportTypeService reportTypeService;

    @Override
    @GetMapping("/get-all")
    public ApiResponse<?> getAll() {
        return new ApiResponse<>(HttpStatus.OK, "", reportTypeService.getAll());
    }
}
