package swp391.reportservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.ReportType;
import swp391.reportservice.dto.response.ReportTypeResponse;

@Component
public class ReportTypeMapper {

    public ReportTypeResponse toResponse(ReportType reportType){
        return ReportTypeResponse.builder()
                .id(reportType.getId())
                .name(reportType.getName())
                .isDeleted(reportType.getIsDeleted())
                .build();
    }
}
