package swp391.ticketservice.service.impl;

import org.springframework.http.HttpStatus;
import swp391.ticketservice.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.Event;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.EventResponse;
import swp391.ticketservice.mapper.EventMapper;
import swp391.ticketservice.repository.EventRepository;
import swp391.ticketservice.service.def.IEventService;
import swp391.ticketservice.config.MessageConfiguration;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final MessageConfiguration messageConfig;

    @Override
    public ApiResponse<?> createEvent(EventRequest eventRequest, MultipartFile file) throws IOException {
        eventRequest.setImage(file.getBytes());
        Event event = eventMapper.toEntity(eventRequest);
        event.setDeleted(Boolean.FALSE);
        eventRepository.save(event);
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_ADD_EVENT, null);
    }

    @Override
    public ApiResponse<List<EventResponse>> getHappeningEvents() {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                eventRepository.getHappeningEvents().stream()
                        .map(eventMapper::toResponse).toList()
        );
    }


}
