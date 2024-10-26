package swp391.reportservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import swp391.entity.ReportFraud;
import swp391.entity.Staff;
import swp391.entity.Ticket;
import swp391.entity.User;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.config.MessageConfiguration;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ApiResponse;
import swp391.reportservice.dto.response.ReportFraudResponse;
import swp391.reportservice.exception.def.NotFoundException;
import swp391.reportservice.mapper.ReportFraudMapper;
import swp391.reportservice.repository.ReportFraudRepository;
import swp391.reportservice.repository.StaffRepository;
import swp391.reportservice.repository.TicketRepository;
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
    private final TicketRepository ticketRepo;
    private final StaffRepository staffRepo;

    @Override
    public ApiResponse<?> create(ReportFraudRequest request) {
        Ticket ticket = ticketRepo.findById(request.getTicketId())
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_INVALID_TICKET + " :" + request.getTicketId()));

        if (reportFraudRepo.findByTicket(ticket).isPresent())
            return new ApiResponse<>(HttpStatus.CONFLICT, messageConfig.ERROR_INVALID_TICKET_EXIST);

        ReportFraud reportFraud = reportFraudRepo.save(fraudMapper.toEntity(request));
        return new ApiResponse<>(HttpStatus.CREATED, messageConfig.SUCCESS_CREATE_REPORT);
    }

    @Override
    public List<ReportFraudResponse> getByAccuser(Long userId) {
        User accuser = userRepo.findById(userId).orElseThrow(
                () -> new NotFoundException(messageConfig.ERROR_ACCUSER + " :" + userId)
        );
        var reportFrauds = reportFraudRepo.findByAccuser(accuser);

        return reportFrauds.stream()
                .map(fraudMapper::toResponse)
                .collect(Collectors.toList());
    }

    //Staff
    @Override
    public List<ReportFraudResponse> getByStaff(Long id) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_STAFF + " :" + id));
        return reportFraudRepo.findByStaffId(id).stream()
                .map(fraudMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApiResponse<?> staffCheckReportReject(Long id, String note) {
        ReportFraud report = reportFraudRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_STAFF + " :" + id));
        report.setNote(note);
        report.setProcess(GeneralProcess.REJECTED);
        reportFraudRepo.save(report);
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_OPERATION);
    }

    @Override
    public ApiResponse<?> staffCheckReportSuccess(Long id, String note) {
        ReportFraud report = reportFraudRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_STAFF + " :" + id));
        report.setNote(note);
        report.setProcess(GeneralProcess.REPORT_PROCESSING);
        reportFraudRepo.save(report);
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_OPERATION);
    }
}
