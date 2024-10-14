package swp391.paymentsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.GenericTicket;
import swp391.entity.ReportFraud;
import swp391.entity.fixed.GeneralProcess;
import swp391.paymentsservice.repository.GenericTicketRepository;
import swp391.paymentsservice.repository.ReportFraudRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentsServiceApplicationTests {

    @Autowired
    private ReportFraudRepository reportFraudRepository;

    @Autowired
    private GenericTicketRepository genericTicketRepository;
    @Test
    void contextLoads() {
        List<GenericTicket> genericTickets = genericTicketRepository.findAll().stream()
                .filter(genericTicket ->
                        isExpiredMoreThanThreeDays(genericTicket.getExpiredDateTime()))
                .toList();


        List<Long> genericTicketsId = new ArrayList<>();
                genericTickets.stream().forEach(genericTicket -> genericTicketsId.add(genericTicket.getId()));

        List<GenericTicket> genericTickets1 = genericTicketRepository.findAllById(genericTicketsId);


//        var reports = reportFraudRepository.getReportByGenericTickets(genericTickets1, GeneralProcess.REPORT_PROCESSING);
//        var reports = reportFraudRepository.getReportByGenericTicketsT1(genericTickets);
//        genericTickets.forEach(genericTicket -> System.out.println(genericTicket.getId()));
//        assertNotNull(reports);
//        assertFalse(reports.isEmpty());
//
//
//        for (ReportFraud report : reports) {
//            System.out.println(report.getProcess() +" " + report.getAccuser().getFirstname() +" "+report.getContent());
//            assertEquals(GeneralProcess.REPORT_PROCESSING, report.getProcess());
//        }
    }

    private boolean isExpiredMoreThanThreeDays(Date expiredDate){
        Date presentDate = getPresentDate();
        if(expiredDate.before(presentDate)){
            long periodMilli= Math.abs(expiredDate.getTime()-presentDate.getTime());
            // 259200000 milliseconds = 3 days
            return periodMilli > 259200000;
        }
        return false;
    }

    private Date getPresentDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

}
