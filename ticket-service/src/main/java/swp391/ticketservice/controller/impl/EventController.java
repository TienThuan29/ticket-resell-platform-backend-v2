package swp391.ticketservice.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.ticketservice.controller.def.IEventController;
import swp391.ticketservice.dto.request.EventFilter;
import swp391.ticketservice.dto.request.EventRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.EventResponse;
import swp391.ticketservice.service.def.IEventService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController implements IEventController {

    private final IEventService eventService;

    @Override
    @PostMapping(value = "/create")
    public ApiResponse<?> createEvent(
            @RequestPart("eventRequest") @Valid EventRequest eventRequest,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return eventService.createEvent(eventRequest, file);
    }

    @Override
    @GetMapping("/get-happening-events")
    public ApiResponse<List<EventResponse>> getHappeningEvents() {
        return eventService.getHappeningEvents();
    }

    @Override
    @GetMapping("/get-happening-events/{categoryName}")
    public ApiResponse<List<EventResponse>> getByCategory(@PathVariable String categoryName) {
        return eventService.getByCategory(categoryName);
    }

    @Override
    @GetMapping("/get-all")
    public ApiResponse<List<EventResponse>> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/get-happening-events/notin")
    public ApiResponse<List<EventResponse>> getEventsNotInAnyCategory() {
        return eventService.getEventsNotInAnyCategory();
    }

    @Override
    @GetMapping("/get-happening-events/filter")
    public ApiResponse<List<EventResponse>> getEventsByFilter(@RequestBody EventFilter eventFilter) {
        return eventService.getEventsByFilter(eventFilter);
    }

//    public ApiResponse<>

}
