package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.entity.Hashtag;
import swp391.ticketservice.config.MessageConfiguration;
import swp391.ticketservice.dto.request.HashtagRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.HashtagResponse;
import swp391.ticketservice.exception.def.NotFoundException;
import swp391.ticketservice.mapper.HashtagMapper;
import swp391.ticketservice.repository.HashtagRepository;
import swp391.ticketservice.service.def.IHashtagService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashtagService implements IHashtagService {

    private final HashtagRepository hashtagRepository;

    private final HashtagMapper hashtagMapper;

    private final MessageConfiguration messageConfig;

    @Override
    public ApiResponse<HashtagResponse> createHashtag(HashtagRequest hashtagRequest) {
        Hashtag savedHashtag = hashtagRepository.save(hashtagMapper.toEntity(hashtagRequest));
        return new ApiResponse<>(
                HttpStatus.OK,
                messageConfig.SUCCESS_ADD_HASHTAG,
                hashtagMapper.toResponse(savedHashtag)
        );
    }

    @Override
    public ApiResponse<HashtagResponse> updateHashtag(Integer id, HashtagRequest hashtagRequest) {
        var hashtag = hashtagRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageConfig.INVALID_HASHTAG_ID)
        );
        hashtag.setName(hashtagRequest.getName());
        return new ApiResponse<>(
                HttpStatus.OK, messageConfig.SUCCESS_UPDATE_HASHTAG,
                hashtagMapper.toResponse(hashtagRepository.save(hashtag))
        );
    }

    @Override
    public ApiResponse<?> deleteHashtag(Integer id) {
        try {
            hashtagRepository.deleteHashtagLogic(id);
        }
        catch (Exception exception) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, messageConfig.FAIL_TO_DELETE_HASHTAG, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_DELETE_HASHTAG, null);
    }

    @Override
    public ApiResponse<List<HashtagResponse>> getAll() {
        return new ApiResponse<>(HttpStatus.OK, "",
                hashtagRepository.getALl().stream().map(hashtagMapper::toResponse).collect(Collectors.toList()));
    }
}
