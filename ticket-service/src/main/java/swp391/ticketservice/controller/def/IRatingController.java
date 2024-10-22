package swp391.ticketservice.controller.def;

import org.springframework.stereotype.Controller;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.ListRatingReponse;

public interface IRatingController {
    ApiResponse<?> createRating(RatingRequest ratingRequest);

    ApiResponse<ListRatingReponse> getListRating(Long sellId);
}
