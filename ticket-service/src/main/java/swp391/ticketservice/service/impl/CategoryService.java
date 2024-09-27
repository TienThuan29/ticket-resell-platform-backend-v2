package swp391.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.CategoryResponse;
import swp391.ticketservice.mapper.CategoryMapper;
import swp391.ticketservice.repository.CategoryRepository;
import swp391.ticketservice.service.def.ICategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public ApiResponse<List<CategoryResponse>> getUsingCategories() {
        return new ApiResponse<>(
                HttpStatus.OK, "",
                categoryRepository.getUsingCategories().stream()
                        .map(categoryMapper::toResponse).toList()
        );
    }

}
