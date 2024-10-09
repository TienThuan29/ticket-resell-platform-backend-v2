package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Event;
import swp391.ticketservice.dto.request.EventRequest;
import swp391.ticketservice.dto.response.EventResponse;
import swp391.ticketservice.utils.DateUtil;
import swp391.ticketservice.utils.ImageUtil;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final  HashtagMapper hashtagMapper;

    public Event toEntity(EventRequest eventRequest) {
        return Event.builder()
                .name(eventRequest.getName())
                .image(ImageUtil.compressImage(eventRequest.getImage()))
                .startDate(DateUtil.fixDateTimeRequest(eventRequest.getStartDate()))
                .endDate(DateUtil.fixDateTimeRequest(eventRequest.getEndDate()))
                .detail(eventRequest.getDetail())
                .isDeleted(Boolean.FALSE)
                .build();
    }

    public EventResponse toResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                //.startDate(DateUtil.fixDateTimeResponse(event.getStartDate()))
                //.endDate(DateUtil.fixDateTimeResponse(event.getEndDate()))
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .image(ImageUtil.decompressImage(event.getImage()))
                .detail(event.getDetail())
                .hashtags(event.getHashtags().stream().map(hashtagMapper::toResponse).toList())
                .build();
    }


}
