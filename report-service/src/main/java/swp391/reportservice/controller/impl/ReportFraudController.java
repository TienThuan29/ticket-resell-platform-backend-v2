package swp391.reportservice.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.config.MessageConfiguration;
import swp391.reportservice.controller.def.IReportFraudController;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ApiResponse;
import swp391.reportservice.dto.response.ReportFraudResponse;
import swp391.reportservice.service.def.IReportFraudService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportFraudController implements IReportFraudController {

    private final IReportFraudService fraudService;
    private final MessageConfiguration messageConfig;

    @GetMapping("/end")
    public String endPoint() {
        return "Report service end point";
    }

    //User
    @Override
    @PostMapping("/create")
    public ApiResponse<?> createReport(
            @RequestPart("reportFraudRequest") @Valid ReportFraudRequest reportFraudRequest,
            @RequestPart("proofFile") MultipartFile proofFile) throws IOException {
        reportFraudRequest.setProof(proofFile.getBytes());
        return new ApiResponse<>(HttpStatus.CREATED, messageConfig.SUCCESS_CREATE_REPORT, fraudService.create(reportFraudRequest));
    }

    @Override
    @GetMapping("/get-by-accuser/{userId}")
    public ApiResponse<List<ReportFraudResponse>> getByAccuser(@PathVariable Long userId) {
        return new ApiResponse<>(HttpStatus.OK, "", fraudService.getByAccuser(userId));
    }

    //Staff
    @Override
    @GetMapping("/get-by-staff/{staffId}")
    public ApiResponse<?> getByStaff(@PathVariable Long staffId) {
        return new ApiResponse<>(HttpStatus.OK, "", fraudService.getByStaff(staffId));
    }

    @Override
    @PutMapping("/staff-check")
    public ApiResponse<?> staffCheckReport(
            @RequestParam Long reportId,
            @RequestParam GeneralProcess process,
            @RequestParam String note) {
        fraudService.staffCheckReport(reportId, process, note);
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_OPERATION);
    }

}
