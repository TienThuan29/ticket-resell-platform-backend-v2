package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import swp391.entity.Rating;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.ListRatingReponse;
import swp391.ticketservice.dto.response.RatingResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.RatingMapper;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.RatingRepository;
import swp391.ticketservice.repository.UserRepository;
import swp391.ticketservice.service.def.IRatingService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    @Override
    public ApiResponse<?> createRating(RatingRequest ratingRequest) {
        ratingRepository.save(ratingMapper.toEntity(ratingRequest));
        return new ApiResponse<>(HttpStatus.OK, "Success");
    }

    @Override
    public ApiResponse<ListRatingReponse> getListRating(Long sellId) {
        Float avgStars= ratingRepository.getAvgStars(sellId);
        List<Rating> listRating = ratingRepository.getListRating(sellId);
        ListRatingReponse listRatingReponse= ListRatingReponse.builder()
                .ratingList(listRating.stream()
                        .map(ratingMapper::toResponse)
                        .toList())
                .avgStars(avgStars)
                .build();
        return new ApiResponse<>(HttpStatus.OK,"Success", listRatingReponse);
    }
}
