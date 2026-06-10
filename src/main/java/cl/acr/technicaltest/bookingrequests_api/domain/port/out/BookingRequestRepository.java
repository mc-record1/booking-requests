package cl.acr.technicaltest.bookingrequests_api.domain.port.out;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequestUpdate;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingSearchCriteria;

import java.util.List;

public interface BookingRequestRepository {

    List<BookingRequest> findAll(BookingSearchCriteria criteria);

    BookingRequest findBy(Long id);

    void save(Long id);

    BookingRequest create(BookingRequest request);

    BookingRequest update(BookingRequestUpdate requestUpdate);

    BookingRequest updateStatus(Long id);
}
