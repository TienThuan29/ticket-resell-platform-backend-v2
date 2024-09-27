package swp391.ticketservice.controller.def;

import org.springframework.web.multipart.MultipartFile;
import swp391.ticketservice.dto.request.EventRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.EventResponse;

import java.io.IOException;
import java.util.List;

public interface IEventController {

    ApiResponse<?> createEvent(EventRequest eventRequest, MultipartFile file)  throws IOException;

    ApiResponse<List<EventResponse>> getHappeningEvents();

}
