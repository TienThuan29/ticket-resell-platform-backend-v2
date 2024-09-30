package swp391.ticketservice.controller.def;

import swp391.ticketservice.dto.request.HashtagRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.HashtagResponse;

public interface IHashtagController {

    ApiResponse<HashtagResponse> createHashtag(HashtagRequest hashtagRequest);

    ApiResponse<HashtagResponse> updateHashtag(Integer id, HashtagRequest hashtagRequest);

    ApiResponse<?> deleteHashtag(Integer id);

}
