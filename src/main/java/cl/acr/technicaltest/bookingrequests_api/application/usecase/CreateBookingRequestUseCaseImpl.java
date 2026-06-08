package cl.acr.technicaltest.bookingrequests_api.application.usecase;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.CreateBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingRequestUseCaseImpl implements CreateBookingRequestUseCase {

    private final BookingRequestRepositoryPort repository;

    public CreateBookingRequestUseCaseImpl(BookingRequestRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public BookingRequest create(BookingRequest request) {
        return null;
    }
}
