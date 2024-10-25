package swp391.ticketservice.controller.impl;

import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.entity.Ticket;
import swp391.entity.fixed.GeneralProcess;
import swp391.ticketservice.controller.def.ITicketController;
import swp391.ticketservice.dto.request.AcceptOrDenySellingRequest;
import swp391.ticketservice.dto.request.TicketRequest;
import swp391.ticketservice.dto.response.ApiResponse;
import swp391.ticketservice.dto.response.TicketResponse;
import swp391.ticketservice.service.def.ITicketService;

import java.io.IOException;
import java.util.List;

/**
 * Author: Nguyen Nhat Truong
 */
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController implements ITicketController {

    private final ITicketService ticketService;

    @GetMapping("/get-all")
    @Override
    public ApiResponse<List<TicketResponse>> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/get/{id}")
    @Override
    public ApiResponse<TicketResponse> getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    @PostMapping("/create")
    @Override
    public ApiResponse<TicketResponse> create(
            @RequestPart("ticketRequest") @Valid TicketRequest ticketRequest,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ticketService.create(ticketRequest, file);
    }

    @PutMapping("/mark-bought/{id}")
    @Override
    public ApiResponse<?> markBought(@PathVariable Long id) {
        return ticketService.markBought(id);
    }

    @Override
    @PutMapping("/mark-delivered-paper-ticket/{ticketId}")
    public ApiResponse<?> markDeliveredPaperTicket(@PathVariable("ticketId") Long ticketId) {
        return ticketService.markDeliveredPaperTicket(ticketId);
    }

    @PutMapping("/mark-staff-check/{id}/{staffId}")
    @Override
    public ApiResponse<?> markStaffCheck(@PathVariable Long id, @PathVariable Long staffId) {
        return ticketService.markStaffCheck(id, staffId);
    }

    @PutMapping("/update-process/{id}/{process}")
    @Override
    public ApiResponse<?> updateProcess(@PathVariable Long id, @PathVariable String process) {
        return ticketService.updateProcess(id, process);
    }

    @GetMapping("/get-tickets-by-process/{process}")
    @Override
    public ApiResponse<List<TicketResponse>> getTicketByProcess(@PathVariable GeneralProcess process) {
        return ticketService.getTicketsByProcess(process);
    }

    @Override
    @PostMapping("/accept/selling-request")
    public ApiResponse<?> acceptToSellTicket(
            @RequestBody AcceptOrDenySellingRequest acceptOrDenySellingRequest
    ) {
        return ticketService.acceptToSellTicket(acceptOrDenySellingRequest);
    }

    @Override
    @PostMapping("/deny/selling-request")
    public ApiResponse<?> denyToSellTicket(@RequestBody AcceptOrDenySellingRequest request) {
        return ticketService.denyToSellTicket(request);
    }

    @Override
    @GetMapping("/get-all-bought-tickets/{id}")
    public ApiResponse<List<TicketResponse>> getAllBoughtTicketsBySeller(@PathVariable("id") Long sellerId) {
        return ticketService.getAllBoughtTicketsBySeller(sellerId);
    }

    @GetMapping("/get-tickets-of-seller/{sellerId}")
    public ApiResponse<List<TicketResponse>> getGenericTicketWithTicketsOfSeller(@PathVariable("sellerId") Long sellerId) {
        return ticketService.getGenericTicketWithTicketsOfSeller(sellerId);
    }

    @GetMapping("/get-all-bought-tickets-of-buyer/{id}")
    public ApiResponse<List<TicketResponse>> getAllBoughtTicketByBuyer(@PathVariable("id") Long buyerId) {
        return ticketService.getAllBoughtTicketsByBuyer(buyerId);
    }
}
