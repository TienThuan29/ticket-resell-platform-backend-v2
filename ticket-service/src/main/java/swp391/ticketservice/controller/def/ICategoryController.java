package swp391.ticketservice.controller.def;

import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryController {

    ApiResponse<List<CategoryResponse>> getUsingCategories();

}
