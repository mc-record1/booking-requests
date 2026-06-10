package cl.acr.technicaltest.bookingrequests_api.domain.port.in;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequestUpdate;

public interface UpdateBookingRequestUseCase {

    BookingRequest update(BookingRequestUpdate bookingRequest);

    BookingRequest updateStatus(Long id);

}
