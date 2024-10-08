package swp391.reportservice.service.def;

import swp391.entity.ReportFraud;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ReportFraudResponse;

import java.util.List;

public interface IReportFraudService {

    //User
    ReportFraudResponse create(ReportFraudRequest request);

    List<ReportFraudResponse> getByAccuser(Long userId);

    //Staff
    List<ReportFraudResponse> getByStaff(Long id);

    void staffCheckReport(Long id, GeneralProcess process, String note);
}
