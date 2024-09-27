package swp391.ticketservice.service.def;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.CategoryResponse;
import swp391.ticketservice.repository.CategoryRepository;

import java.util.List;


public interface ICategoryService {

    ApiResponse<List<CategoryResponse>> getUsingCategories();

}
