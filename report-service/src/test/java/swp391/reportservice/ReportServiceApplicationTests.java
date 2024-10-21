package swp391.reportservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swp391.entity.ReportFraud;
import swp391.reportservice.dto.request.ReportFraudRequest;
import swp391.reportservice.dto.response.ReportFraudResponse;
import swp391.reportservice.repository.ReportFraudRepository;
import swp391.reportservice.service.def.IReportFraudService;
import swp391.reportservice.dto.response.ApiResponse;
import swp391.reportservice.service.impl.ReportFraudService;

import java.util.List;
import java.util.Random;

@SpringBootTest
class ReportServiceApplicationTests {

    @Autowired
    private ReportFraudService reportService;

    @Autowired
    private ReportFraudRepository reportRepository;

    private ReportFraud report;

    @BeforeEach
    void setUp() {
        // Set up a report for testing
        report = reportRepository.findAll().get(0);
    }

    @Test
    void contextLoads() {
        // This test checks if the application context loads successfully
    }

    @Test
    void testGetAllReports() {
        List<ReportFraudResponse> response = reportService.getByStaff(7L);
        Assertions.assertNotNull(response);
    }

    @Test
    void testCreateReport() {
        Random rd = new Random();
        byte[] arr = new byte[7];
        rd.nextBytes(arr);
        ReportFraudRequest newReport = ReportFraudRequest.builder()
                .reportTypeId(1)
                .accuserId(7L)
                .content("Report test")
                .proof(arr)
                .ticketId(17L)
                .staffId(4L)
                .build();
        
        ApiResponse<?> response = reportService.create(newReport);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(201, response.getStatusCode().value(), "The report should be created successfully");
        Assertions.assertEquals("New Test Report", response.getBody(), "The report title should match");
    }


}
