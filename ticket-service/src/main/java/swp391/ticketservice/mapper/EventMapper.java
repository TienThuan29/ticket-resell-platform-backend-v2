package swp391.ticketservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.Event;
import swp391.ticketservice.dto.request.EventRequest;
import swp391.ticketservice.dto.response.EventResponse;
import swp391.ticketservice.utils.ImageUtil;

@Component
public class EventMapper {

    public Event toEntity(EventRequest eventRequest) {
        return Event.builder()
                .name(eventRequest.getName())
                .image(ImageUtil.compressImage(eventRequest.getImage()))
                .startDate(eventRequest.getStartDate())
                .endDate(eventRequest.getEndDate())
                .detail(eventRequest.getDetail())
                .build();
    }

    public EventResponse toResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .image(ImageUtil.decompressImage(event.getImage()))
                .detail(event.getDetail())
                .build();
    }


}
