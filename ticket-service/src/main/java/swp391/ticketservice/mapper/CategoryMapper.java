package swp391.ticketservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.Category;
import swp391.ticketservice.dto.response.CategoryResponse;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .isDeleted(category.isDeleted())
                .build();
    }

}
