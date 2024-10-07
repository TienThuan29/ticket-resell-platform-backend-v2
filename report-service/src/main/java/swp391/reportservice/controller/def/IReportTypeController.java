package swp391.reportservice.controller.def;

import swp391.reportservice.dto.response.ApiResponse;

public interface IReportTypeController {
    ApiResponse<?> getAll();
}
