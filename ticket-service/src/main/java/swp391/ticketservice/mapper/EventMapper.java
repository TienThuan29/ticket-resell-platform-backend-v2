package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Event;
import swp391.entity.Hashtag;
import swp391.ticketservice.dto.request.EventRequest;
import swp391.ticketservice.dto.request.HashtagRequest;
import swp391.ticketservice.dto.response.EventResponse;
import swp391.ticketservice.repository.HashtagRepository;
import swp391.ticketservice.utils.DateUtil;
import swp391.ticketservice.utils.ImageUtil;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final  HashtagMapper hashtagMapper;

    private final HashtagRepository hashtagRepository;

    public Event toEntity(EventRequest eventRequest) {
        List<Integer> hashtags= eventRequest.getHashtagsRequest().stream().
                map(HashtagRequest::getId).toList();

        return Event.builder()
                .name(eventRequest.getName())
                .image(ImageUtil.compressImage(eventRequest.getImage()))
                .startDate(DateUtil.fixDateTimeRequest(eventRequest.getStartDate()))
                .endDate(DateUtil.fixDateTimeRequest(eventRequest.getEndDate()))
                .detail(eventRequest.getDetail())
                .isDeleted(Boolean.FALSE)
                .hashtags(hashtagRepository.findAllById(hashtags))
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
