package swp391.reportservice.controller.def;

import org.springframework.web.multipart.MultipartFile;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ApiResponse;

import java.io.IOException;

public interface IReportFraudController {

    ApiResponse<?> createReport(ReportFraudRequest request, MultipartFile proofFile) throws IOException;

    ApiResponse<?> getByAccuser(Long userId);

    ApiResponse<?> getByStaff(Long staffId);

    ApiResponse<?> staffCheckReportReject(Long reportId, String note);

    ApiResponse<?> staffCheckReportSuccess(Long reportId, String note);
}
