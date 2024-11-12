package swp391.reportservice.service.def;

import swp391.entity.ReportFraud;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ApiResponse;
import swp391.reportservice.dto.response.ReportFraudResponse;

import java.util.Date;
import java.util.List;

public interface IReportFraudService {

    //User
    ApiResponse<?> create(ReportFraudRequest request);

    List<ReportFraudResponse> getByAccuser(Long userId);

    //Staff
    List<ReportFraudResponse> getByStaff(Long id);

    ApiResponse<?> staffCheckReportReject(Long id, String note);

    ApiResponse<?> staffCheckReportSuccess(Long id, String note);

    ApiResponse<?> updateDateExpiredById(Long id, Date dateExpired);
}
