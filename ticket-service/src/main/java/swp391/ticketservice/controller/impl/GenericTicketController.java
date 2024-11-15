package swp391.ticketservice.controller.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391.ticketservice.controller.def.IGenericTicketController;
import swp391.ticketservice.dto.request.GenericTicketFilter;
import swp391.ticketservice.dto.request.GenericTicketRequest;
import swp391.ticketservice.dto.request.OrderTicketRequest;
import swp391.ticketservice.dto.response.GenericTicketResponse;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.OrderTicketResponse;
import swp391.ticketservice.service.def.IGenericTicketService;
import java.util.Date;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 * Author: Nguyen Tien Thuan
 */
@RestController
@RequestMapping("/api/tickets/generic")
public class GenericTicketController implements IGenericTicketController {

    @Autowired
    private IGenericTicketService genericTicketService;

    @Override
    @PostMapping("/create")
    public ApiResponse<GenericTicketResponse> createGenericTicket(
            @RequestBody @Valid GenericTicketRequest genericTicketRequest
    ) {
        return genericTicketService.create(genericTicketRequest);
    }

    @Override
    @PutMapping("/update-all/{id}")
    public ApiResponse<GenericTicketResponse> updateGenericTicket(
            @PathVariable("id") Long id, @RequestBody @Valid GenericTicketRequest genericTicketRequest
    ) {
        return genericTicketService.updateAllFields(id, genericTicketRequest);
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
    public ApiResponse<List<GenericTicketResponse>> getByEvent(@PathVariable("eventId") Integer eventId) {
        return genericTicketService.getByEvent(eventId);
    }

    @Override
    @GetMapping("/get-total-ticket/{genericTicketId}")
    public ApiResponse<Integer> getTotalTicketsInGenericTicket(@PathVariable("genericTicketId") Long genericTicketId) {
        return genericTicketService.getTotalTicketsInGenericTicket(genericTicketId);
    }

    @GetMapping("/get-all-by-seller/{sellerId}")
    public ApiResponse<List<GenericTicketResponse>> getAllGenericTicketBySeller(@PathVariable("sellerId") Long sellerId) {
        return genericTicketService.getAllGenericTicketBySeller(sellerId);
    }

    @GetMapping("/get-by-filter")
    @Override
    public ApiResponse<List<GenericTicketResponse>> getByFilter(@RequestBody GenericTicketFilter genericTicketFilter) {
        return genericTicketService.getByFilter(genericTicketFilter);
    }

    @Override
    @PostMapping("/order")
    public ApiResponse<?> orderTicket(@RequestBody OrderTicketRequest orderTicketRequest) {
        return genericTicketService.orderTicket(orderTicketRequest);
    }

    @Override
    @PostMapping("/cancel-order")
    public ApiResponse<?> cancelTicketOrder(
            @RequestParam("orderNo") String orderNo,
            @RequestParam("senderId") Long senderId
    ) {
        return genericTicketService.cancelTicketOrder(orderNo, senderId);
    }

    @Override
    @GetMapping("/get-processing-order-ticket/{id}")
    public ApiResponse<List<OrderTicketResponse>> getProcessingOrderTicket(@PathVariable("id") Long userId) {
        return genericTicketService.getProcessingOrderTicket(userId);
    }

    @Override
    @GetMapping("/get-canceled-order-ticket/{id}")
    public ApiResponse<List<OrderTicketResponse>> getCanceledOrderTicket(@PathVariable("id") Long userId) {
        return genericTicketService.getCanceledOrderTicket(userId);
    }

    @Override
    @GetMapping("/get-all-request-order-ticket/{id}")
    public ApiResponse<List<OrderTicketResponse>> getAllOrderTicketRequest(@PathVariable("id") Long sellerId) {
        return genericTicketService.getAllOrderTicketRequest(sellerId);
    }

    @Override
    @GetMapping("/get-rated-generic-ticket-of-buyer/{buyerId}")
    public ApiResponse<List<GenericTicketResponse>> getRatedGenericTicket(@PathVariable("buyerId") Long buyerId) {
        return genericTicketService.getRatedGenericTicket(buyerId);
    }

}
