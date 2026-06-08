package cl.acr.technicaltest.bookingrequests_api.domain.port.in;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;

public interface CreateBookingRequestUseCase {

    BookingRequest create(BookingRequest request);
}
