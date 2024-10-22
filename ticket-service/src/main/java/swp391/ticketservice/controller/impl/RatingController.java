package swp391.ticketservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391.ticketservice.controller.def.IRatingController;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.ListRatingReponse;
import swp391.ticketservice.service.def.IRatingService;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController implements IRatingController {

    private final IRatingService ratingService;

    @Override
    @PostMapping("/create")
    public ApiResponse<?> createRating(@RequestBody RatingRequest ratingRequest) {
        return ratingService.createRating(ratingRequest);
    }

    @Override
    @GetMapping("/getRates")
    public ApiResponse<ListRatingReponse> getListRating(Long sellId) {
        return ratingService.getListRating(sellId);
    }
}
