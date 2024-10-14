package swp391.staffservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Event;
import swp391.staffservice.dto.response.EventResponse;
import swp391.staffservice.utils.DateUtil;
import swp391.staffservice.utils.ImageUtil;

@Component
@RequiredArgsConstructor
public class EventMapper {

    public EventResponse toResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(DateUtil.fixDateTime(event.getStartDate()))
                .endDate(DateUtil.fixDateTime(event.getEndDate()))
                .image(ImageUtil.decompressImage(event.getImage()))
                .detail(event.getDetail())
//                .hashtags(event.getHashtags().stream().map(hashtagMapper::toResponse).toList())
                .build();
    }


}
