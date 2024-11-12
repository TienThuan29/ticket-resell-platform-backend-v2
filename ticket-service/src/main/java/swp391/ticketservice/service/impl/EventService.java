package swp391.ticketservice.service.impl;

import org.springframework.http.HttpStatus;
import swp391.entity.Hashtag;
import swp391.ticketservice.dto.request.EventFilter;
import swp391.ticketservice.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.Event;
import swp391.ticketservice.dto.request.HashtagRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.EventResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.EventMapper;
import swp391.ticketservice.repository.EventRepository;
import swp391.ticketservice.repository.HashtagRepository;
import swp391.ticketservice.service.def.IEventService;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.utils.DateUtil;
import swp391.ticketservice.utils.ImageUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    private final HashtagRepository hashtagRepository;

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

    @Override
    public ApiResponse<List<EventResponse>> getByCategory(String categoryName) {
        return new ApiResponse<>(
                HttpStatus.OK,
                "",
                eventRepository.getEventsByCategory(categoryName).stream()
                        .map(eventMapper::toResponse)
                        .collect(Collectors.toList())
        ) ;
    }

    @Override
    public ApiResponse<List<EventResponse>> getEventsNotInAnyCategory() {
        return new ApiResponse<>(
                HttpStatus.OK,
                "",
                eventRepository.getOtherEvent().stream()
                        .map(eventMapper::toResponse).toList()
        );
    }

    @Override
    public ApiResponse<List<EventResponse>> getAllEvents() {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                eventRepository.findAll().stream()
                        .map(eventMapper::toResponse).toList()
        );
    }

    @Override
    public ApiResponse<List<EventResponse>> getEventsByFilter(EventFilter eventFilter) {
//        List<Hashtag> hashtags= eventFilter.getHashtagIds() == null ?
//                null:hashtagRepository.findAllById(eventFilter.getHashtagIds());

        var searchResult = eventRepository.getEventByFilter(
                eventFilter.getName(),
                eventFilter.getStartDate(),
                eventFilter.getHashtagIds()
        );
        return new ApiResponse<>(HttpStatus.OK, "",
                searchResult.stream()
                        .map(eventMapper::toResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    public ApiResponse<List<EventResponse>> getHotEvents() {
        var hotHashtag = hashtagRepository.getHashtagByNameAndDeleted("Hot", Boolean.FALSE).orElseThrow();
        List<EventResponse> events = eventRepository.getHappeningEvents()
                .stream().filter(
                        event -> event.getHashtags().contains(hotHashtag)
                ).map(
                        eventMapper::toResponse
                ).toList();
        return new ApiResponse<>(HttpStatus.OK, "", events);
    }

    @Override
    public ApiResponse<List<EventResponse>> getSpecialEvent() {
        var hotHashtag = hashtagRepository.getHashtagByNameAndDeleted("DacBiet", Boolean.FALSE).orElseThrow();
        List<EventResponse> events = eventRepository.getHappeningEvents()
                .stream().filter(
                        event -> event.getHashtags().contains(hotHashtag)
                                && event.getEndDate().after(new Date())
                ).map(
                        eventMapper::toResponse
                ).toList();
        return new ApiResponse<>(HttpStatus.OK, "", events);
    }

    @Override
    public ApiResponse<?> updateEvent(Integer eventId, EventRequest updateEventRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(messageConfig.INVALID_EVENT+" :"+eventId));

        List<Integer> hashtags = updateEventRequest.getHashtagsRequest()
                .stream().map(HashtagRequest::getId).toList();

        event.setName(updateEventRequest.getName());
        //event.setImage(ImageUtil.compressImage(updateEventRequest.getImage()));
        event.setStartDate(DateUtil.fixDateTimeRequest(updateEventRequest.getStartDate()));
        event.setEndDate(DateUtil.fixDateTimeRequest(updateEventRequest.getEndDate()));
        event.setDetail(updateEventRequest.getDetail());
        event.setHashtags(hashtagRepository.findAllById(hashtags));

        eventRepository.save(event);
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_UPDATE_EVENT);
    }

}
