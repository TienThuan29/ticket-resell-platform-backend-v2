package swp391.reportservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.ReportFraud;
import swp391.entity.fixed.GeneralProcess;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ReportFraudResponse;
import swp391.reportservice.repository.*;
import swp391.reportservice.utils.ImageUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ReportFraudMapper {

    private final UserRepository userRepo;
    private final ReportFraudRepository reportFraudRepo;
    private final TicketRepository ticketRepo;
    private final ReportTypeRepository reportTypeRepo;
    private final GenericTicketRepository genericRepo;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;
    private final ReportTypeMapper reportTypeMapper;

    public ReportFraud toEntity(ReportFraudRequest reportFraudRequest){
        var ticket= ticketRepo.findById(reportFraudRequest.getTicketId()).get();
        Long staffId= ticket.getVerifyStaff().getId();
        Long reportedUserId= genericRepo.findById(ticket.getGenericTicket().getId()).get().getSeller().getId();

        return ReportFraud.builder()
                .proof(ImageUtil.compressImage(reportFraudRequest.getProof()))
                .content(reportFraudRequest.getContent())
                .process(GeneralProcess.WAITING)
                .reportType(reportTypeRepo.findById(reportFraudRequest.getReportTypeId()).get())
                .staffId(staffId)
                .reportedUserId(reportedUserId)
                .ticket(ticket)
                .accuser(userRepo.findById(reportFraudRequest.getAccuserId()).get())
                .build();
    }

    public ReportFraudResponse toResponse(ReportFraud reportFraud){
        return ReportFraudResponse.builder()
                .id(reportFraud.getId())
                .content(reportFraud.getContent())
                .proof(Base64.getEncoder().encodeToString(ImageUtil.decompressImage(reportFraud.getProof())))
                .process(reportFraud.getProcess().content)
                .note(reportFraud.getNote())
                .reportType(reportTypeMapper.toResponse(reportFraud.getReportType()))
                .staffId(reportFraud.getStaffId())
                .reportedUser(userMapper.toUserResponse(userRepo.findById(reportFraud.getReportedUserId()).get()))
                .ticket(ticketMapper.toResponse(reportFraud.getTicket()))
                .accuser(userMapper.toUserResponse(reportFraud.getAccuser()))
                .build();
    }
}
