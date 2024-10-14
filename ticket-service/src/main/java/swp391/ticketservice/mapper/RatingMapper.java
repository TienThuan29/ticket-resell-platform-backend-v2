package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Rating;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.RatingResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.RatingRepository;
import swp391.ticketservice.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class RatingMapper {

    private final UserRepository userRepository;
    private final GenericTicketRepository genericTicketRepository;
    private final UserMapper userMapper;
    private final GenericTicketMapper genericTicketMapper;

    public Rating toEntity(RatingRequest ratingRequest){
        var user = userRepository.findById(ratingRequest.getBuyerId()).orElseThrow(() -> new NotFoundException("Không tìm thấy người mua"));
        var genericTicket = genericTicketRepository.findById(ratingRequest.getGenericTicketId()).orElseThrow(() -> new NotFoundException("Không tìm thấy vé"));
        return Rating.builder()
                .buyer(user)
                .genericTicket(genericTicket)
                .comment(ratingRequest.getComment())
                .stars(ratingRequest.getStars())
                .build();
    }

    public RatingResponse toResponse(Rating rating){
        return RatingResponse.builder()
                .stars(rating.getStars())
                .comment(rating.getComment())
                .buyer(userMapper.toBuyerResponse(rating.getBuyer()))
                .genericTicket(genericTicketMapper.toResponse(rating.getGenericTicket()))
                .build();
    }
}