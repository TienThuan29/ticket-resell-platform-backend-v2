package swp391.ticketservice.service.def;

import org.springframework.stereotype.Service;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.ListRatingReponse;
import swp391.ticketservice.dto.response.RatingResponse;

@Service
public interface IRatingService {
    ApiResponse<?> createRating(RatingRequest ratingRequest);

    ApiResponse<ListRatingReponse> getListRating(Long sellId);
}
