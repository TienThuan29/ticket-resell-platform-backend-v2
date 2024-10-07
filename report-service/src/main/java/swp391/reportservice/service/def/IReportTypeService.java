package swp391.reportservice.service.def;

import org.springframework.stereotype.Service;
import swp391.entity.ReportType;
import swp391.reportservice.dto.response.ReportTypeResponse;

import java.util.List;

@Service
public interface IReportTypeService {
    List<ReportTypeResponse> getAll();
}
