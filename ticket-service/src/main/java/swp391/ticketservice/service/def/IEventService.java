package swp391.ticketservice.service.def;

import swp391.ticketservice.dto.request.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.EventResponse;

import java.io.IOException;
import java.util.List;

public interface IEventService {

    ApiResponse<?> createEvent(EventRequest eventRequest, MultipartFile file) throws IOException;

    ApiResponse<List<EventResponse>> getHappeningEvents();

    ApiResponse<List<EventResponse>> getByCategory(String categoryName);

    ApiResponse<List<EventResponse>> getEventsNotInAnyCategory();

    ApiResponse<List<EventResponse>> getAllEvents();

    ApiResponse<List<EventResponse>> getEventsByFilter(EventFilter eventFilter);

    ApiResponse<List<EventResponse>> getHotEvents();

    ApiResponse<List<EventResponse>> getSpecialEvent();

    ApiResponse<?> updateEvent(Integer eventId, EventRequest eventRequest);
}
