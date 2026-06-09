package cl.acr.technicaltest.bookingrequests_api.application.usecase;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.UpdateBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookingRequestUseCaseImpl implements UpdateBookingRequestUseCase {

    private final BookingRequestRepository repository;

    public UpdateBookingRequestUseCaseImpl(BookingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookingRequest update(BookingRequest bookingRequest) {
        return repository.update(bookingRequest);
    }

    @Override
    public BookingRequest updateStatus(Long id) {
        return repository.updateStatus(id);
    }
}
