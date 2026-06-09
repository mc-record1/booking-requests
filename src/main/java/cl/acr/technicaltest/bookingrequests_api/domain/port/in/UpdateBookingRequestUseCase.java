package cl.acr.technicaltest.bookingrequests_api.domain.port.in;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;

public interface UpdateBookingRequestUseCase {

    BookingRequest update(BookingRequest bookingRequest);

    BookingRequest updateStatus(Long id);

}
