package cl.acr.technicaltest.bookingrequests_api.presentation.controllers;

import cl.acr.technicaltest.bookingrequests_api.presentation.response.BookingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingRequestController {

    @GetMapping("/bookings")
    public List<BookingResponse> findBookings(
            @RequestParam(required = false) String taxId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String freightMode,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(required = false) String bookingCode
    ) {

        BookingResponse response = new BookingResponse();

        response.setId(1L);
        response.setBookingCode("BK-20250601");
        response.setIssueDate(LocalDate.now());
        response.setStatus("CONFIRMED");
        response.setFreightMode("SEA");
        response.setSupplierName("ACME LTDA");
        response.setFobValue(new BigDecimal("15000.50"));

        return List.of(response);
    }

}
