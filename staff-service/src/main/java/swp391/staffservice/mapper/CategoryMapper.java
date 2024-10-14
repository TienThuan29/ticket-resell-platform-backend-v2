package swp391.staffservice.mapper;

import org.springframework.stereotype.Component;
import swp391.entity.Category;
import swp391.staffservice.dto.response.CategoryResponse;

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
