package swp391.reportservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.reportservice.dto.response.ReportTypeResponse;
import swp391.reportservice.mapper.ReportTypeMapper;
import swp391.reportservice.repository.ReportTypeRepository;
import swp391.reportservice.service.def.IReportTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportTypeService implements IReportTypeService {

    private final ReportTypeRepository reportTypeRepo;
    private final ReportTypeMapper reportTypeMapper;

    @Override
    public List<ReportTypeResponse> getAll() {
        return reportTypeRepo.findAll().stream()
                .map(reportTypeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
