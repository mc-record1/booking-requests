package cl.acr.technicaltest.bookingrequests_api.domain.port.in;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingSearchCriteria;


import java.util.List;

public interface GetBookingRequestUseCase {

    List<BookingRequest> findAll(BookingSearchCriteria request);

    BookingRequest findById(Long id);
}
