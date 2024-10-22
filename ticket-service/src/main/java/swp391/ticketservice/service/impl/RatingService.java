package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import swp391.entity.Rating;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.ListRatingReponse;
import swp391.ticketservice.mapper.RatingMapper;
import swp391.ticketservice.repository.RatingRepository;
import swp391.ticketservice.service.def.IRatingService;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;

    private final RatingMapper ratingMapper;

    private final MessageConfiguration messageConfig;

    @Override
    public ApiResponse<?> createRating(RatingRequest ratingRequest) {
        try {
            ratingRepository.save(ratingMapper.toEntity(ratingRequest));
        }
        catch(Exception ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_RATING);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_RATING);
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
