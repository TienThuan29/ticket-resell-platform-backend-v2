package swp391.reportservice.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportFraudService {

    @GetMapping("/end")
    public String endPoint() {
        return "Report service end point";
    }

}
