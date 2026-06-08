package cl.acr.technicaltest.bookingrequests_api.domain.port.out;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;

public interface BookingRequestRepositoryPort {

    BookingRequest save(BookingRequest bookingRequest);
}
