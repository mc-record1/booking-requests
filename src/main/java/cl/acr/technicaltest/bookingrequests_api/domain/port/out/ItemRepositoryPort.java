package cl.acr.technicaltest.bookingrequests_api.domain.port.out;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingItem;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;

public interface ItemRepositoryPort {
    BookingItem save(BookingRequest BookingItem);
}
