package cl.acr.technicaltest.bookingrequests_api.presentation.controllers;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.CreateBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.DeleteBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.GetBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.UpdateBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.presentation.mappers.BookingSearchMapper;
import cl.acr.technicaltest.bookingrequests_api.presentation.request.UpdateBookingRequest;
import cl.acr.technicaltest.bookingrequests_api.presentation.request.BookingSearchRequest;
import cl.acr.technicaltest.bookingrequests_api.presentation.request.CreateBookingRequest;
import cl.acr.technicaltest.bookingrequests_api.presentation.response.BookingResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingRequestController {

    private final GetBookingRequestUseCase getBookingRequestUseCase;
    private final CreateBookingRequestUseCase createBookingRequestUseCase;
    private final DeleteBookingRequestUseCase deleteBookingRequestUseCase;
    private final UpdateBookingRequestUseCase updateBookingRequestUseCase;
    private final BookingSearchMapper bookingSearchMapper;


    public BookingRequestController(GetBookingRequestUseCase getBookingRequestUseCase,
                                    CreateBookingRequestUseCase createBookingRequestUseCase,
                                    DeleteBookingRequestUseCase deleteBookingRequestUseCase,
                                    UpdateBookingRequestUseCase updateBookingRequestUseCase,
                                    BookingSearchMapper bookingSearchMapper) {
        this.getBookingRequestUseCase = getBookingRequestUseCase;
        this.createBookingRequestUseCase = createBookingRequestUseCase;
        this.deleteBookingRequestUseCase = deleteBookingRequestUseCase;
        this.updateBookingRequestUseCase = updateBookingRequestUseCase;
        this.bookingSearchMapper = bookingSearchMapper;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponse>> findBookingAll(@Valid BookingSearchRequest request) {
        return ResponseEntity.ok(
                bookingSearchMapper.toResponseList(
                        getBookingRequestUseCase.findAll(
                                bookingSearchMapper.toCriteria(request)
                        )
                )
        );
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> create(@RequestBody @Valid CreateBookingRequest request) {
        return ResponseEntity.ok(bookingSearchMapper.toResponse(
                createBookingRequestUseCase.create(
                        bookingSearchMapper.toRequestCreate(request)))
        );
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponse> findBookingById(@PathVariable Long id) {
        BookingRequest bookingRequest = getBookingRequestUseCase.findById(id);

        return ResponseEntity.ok(
                bookingSearchMapper.toResponse(bookingRequest)
        );
    }


    @PatchMapping("/bookings/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateBookingRequest requestUpdate) {
        return ResponseEntity.ok(bookingSearchMapper.toResponse(
                updateBookingRequestUseCase.update(
                        bookingSearchMapper.toRequestUpdate(id, requestUpdate)))
        );
    }

    @PatchMapping("/bookings/{id}/status")
    public ResponseEntity<BookingResponse> changeStatus(@PathVariable Long id, String status) {
        return ResponseEntity.ok(bookingSearchMapper.toResponse(
                updateBookingRequestUseCase.updateStatus(id))
        );
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteBookingRequestUseCase.deleteBookingRequest(id);
        return ResponseEntity.ok().build();
    }
}
