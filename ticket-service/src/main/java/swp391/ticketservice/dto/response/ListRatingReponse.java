package swp391.ticketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListRatingReponse {
    @JsonProperty("ratingList")
    private List<RatingResponse> ratingList;

    @JsonProperty("avgStars")
    private Float avgStars;
}
