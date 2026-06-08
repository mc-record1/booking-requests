package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.adapter;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class BookingRequestRepositoryAdapter implements BookingRequestRepositoryPort {
    @Override
    public BookingRequest save(BookingRequest bookingRequest) {
        return null;
    }
}
