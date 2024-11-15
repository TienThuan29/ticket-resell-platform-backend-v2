package swp391.ticketservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swp391.entity.Rating;
import swp391.entity.embedable.RatingID;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.RatingRequest;
import swp391.ticketservice.dto.response.RatingResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.repository.GenericTicketRepository;
import swp391.ticketservice.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class RatingMapper {

    private final UserRepository userRepository;

    private final GenericTicketRepository genericTicketRepository;

    private final UserMapper userMapper;

    private final GenericTicketMapper genericTicketMapper;

    private final MessageConfiguration messageConfig;

    public Rating toEntity(RatingRequest ratingRequest){
        var user = userRepository.findById(ratingRequest.getBuyerId())
                .orElseThrow(() -> new NotFoundException(messageConfig.INVALID_BUYER));
        var genericTicket = genericTicketRepository.findById(ratingRequest.getGenericTicketId())
                .orElseThrow(() -> new NotFoundException(messageConfig.INVALID_GENERICTICKET));

        return Rating.builder()
                .ratingID(new RatingID(
                        ratingRequest.getGenericTicketId(),
                        ratingRequest.getBuyerId())
                )
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
