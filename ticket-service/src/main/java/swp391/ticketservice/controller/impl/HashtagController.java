package swp391.ticketservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import swp391.ticketservice.controller.def.IHashtagController;
import swp391.ticketservice.dto.request.HashtagRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.HashtagResponse;
import swp391.ticketservice.service.def.IHashtagService;

@RestController
@RequestMapping("/api/hashtags")
@RequiredArgsConstructor
public class HashtagController implements IHashtagController {

    private final IHashtagService hashtagService;

    @Override
    @PostMapping("/create")
    public ApiResponse<HashtagResponse> createHashtag(@RequestBody HashtagRequest hashtagRequest) {
        return hashtagService.createHashtag(hashtagRequest);
    }

    @Override
    @PutMapping("/update/name/{id}")
    public ApiResponse<HashtagResponse> updateHashtag(
            @PathVariable("id") Integer id,
            @RequestBody HashtagRequest hashtagRequest
    ) {
        return hashtagService.updateHashtag(id, hashtagRequest);
    }


    @Override
    @DeleteMapping("/logic-delete/{id}")
    public ApiResponse<?> deleteHashtag(@PathVariable("id") Integer id) {
        return hashtagService.deleteHashtag(id);
    }

}
