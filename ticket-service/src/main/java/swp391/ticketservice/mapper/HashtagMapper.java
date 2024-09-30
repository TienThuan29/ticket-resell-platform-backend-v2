package swp391.ticketservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.Hashtag;
import swp391.ticketservice.dto.request.HashtagRequest;
import swp391.ticketservice.dto.response.HashtagResponse;

@Component("ticketServiceHashtagMapper")
public class HashtagMapper {

    public HashtagResponse toResponse(Hashtag hashtag) {
        return HashtagResponse.builder()
                .id(hashtag.getId())
                .name(hashtag.getName())
                .isDeleted(hashtag.isDeleted())
                .build();
    }


    public Hashtag toEntity(HashtagRequest hashtagRequest) {
        return Hashtag.builder()
                .name(hashtagRequest.getName())
                .isDeleted(Boolean.FALSE)
                .build();
    }

}
