package swp391.reportservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.ReportFraud;
import swp391.entity.User;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.config.MessageConfiguration;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ReportFraudResponse;
import swp391.reportservice.exception.def.NotFoundException;
import swp391.reportservice.mapper.ReportFraudMapper;
import swp391.reportservice.repository.ReportFraudRepository;
import swp391.reportservice.repository.UserRepository;
import swp391.reportservice.service.def.IReportFraudService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportFraudService implements IReportFraudService {

    private final ReportFraudMapper fraudMapper;
    private final UserRepository userRepo;
    private final ReportFraudRepository reportFraudRepo;
    private final MessageConfiguration messageConfig;

    @Override
    public ReportFraudResponse create(ReportFraudRequest request) {
        ReportFraud reportFraud= reportFraudRepo.save(fraudMapper.toEntity(request));
        return fraudMapper.toResponse(reportFraud);
    }

    @Override
    public List<ReportFraudResponse> getByAccuser(Long userId) {
        User accuser= userRepo.findById(userId).orElseThrow(
                () -> new NotFoundException(messageConfig.ERROR_ACCUSER+" :"+userId)
        );
        var reportFrauds= reportFraudRepo.findByAccuser(accuser);

        return reportFrauds.stream()
                .map(fraudMapper::toResponse)
                .collect(Collectors.toList());
    }

    //Staff
    @Override
    public List<ReportFraudResponse> getByStaff(Long id) {
        return reportFraudRepo.findByStaffId(id).stream()
                .map(fraudMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void staffCheckReport(Long id, GeneralProcess process, String note) {
        ReportFraud report= reportFraudRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_STAFF+" :"+id));
        report.setProcess(process);
        report.setNote(note);
        reportFraudRepo.save(report);
    }
}
