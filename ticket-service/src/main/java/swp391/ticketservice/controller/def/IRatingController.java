package swp391.ticketservice.controller.def;

import org.springframework.stereotype.Controller;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.ApiResponse;

public interface IRatingController {
    ApiResponse<?> createRating(RatingRequest ratingRequest);

    ApiResponse<?> getListRating(Long sellId);
}
