package swp391.ticketservice.controller.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391.ticketservice.controller.def.IGenericTicketController;
import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.service.def.IGenericTicketService;
import java.util.Date;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 */
@RestController
@RequestMapping("/api/tickets/generic")
public class GenericTicketController implements IGenericTicketController {

    @Autowired
    private IGenericTicketService genericTicketService;

    @PostMapping("/create")
    @Override
    public ApiResponse<GenericTicketResponse> createGenericTicket(
            @RequestBody @Valid GenericTicketRequest genericTicketRequest
    ) {
        return genericTicketService.create(genericTicketRequest);
    }

    @Override
    @PutMapping("/update-price-expired/{id}")
    public ApiResponse<?> updatePriceAndExpiredDate(
            @PathVariable Long id,
            @RequestParam @Min(value = 0) Long price,
            @RequestParam Date date
    ) {
        return genericTicketService.updatePriceAndExpiredDate(id, price, date);
    }

    @GetMapping("/get-all")
    @Override
    public ApiResponse<List<GenericTicketResponse>> getAll() {
        return genericTicketService.getAll();
    }

    @GetMapping("/get/{id}")
    @Override
    public ApiResponse<GenericTicketResponse> getById(@PathVariable Long id) {
        return genericTicketService.getById(id);
    }

    @GetMapping("/get-by-category/{categoryId}")
    @Override
    public ApiResponse<List<GenericTicketResponse>> getByCategory(@PathVariable Integer categoryId) {
        return genericTicketService.getByCategory(categoryId);
    }

    @GetMapping("/get-by-event/{eventId}")
    @Override
    public ApiResponse<List<GenericTicketResponse>> getByEvent(@PathVariable Integer eventId) {
        return genericTicketService.getByEvent(eventId);
    }

}
