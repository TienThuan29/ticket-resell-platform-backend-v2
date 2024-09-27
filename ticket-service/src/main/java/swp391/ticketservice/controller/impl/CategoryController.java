package swp391.ticketservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.ticketservice.controller.def.ICategoryController;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.CategoryResponse;
import swp391.ticketservice.service.def.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CategoryController implements ICategoryController {

    private final ICategoryService categoryService;

    @Override
    @GetMapping("/get-using-cate")
    public ApiResponse<List<CategoryResponse>> getUsingCategories() {
        return categoryService.getUsingCategories();
    }
}
