package cl.acr.technicaltest.bookingrequests_api.application.usecase;

import cl.acr.technicaltest.bookingrequests_api.domain.port.in.DeleteBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookingRequestUseCaseImpl implements DeleteBookingRequestUseCase {

    private final BookingRequestRepository repository;

    public DeleteBookingRequestUseCaseImpl(BookingRequestRepository repository) {
        this.repository = repository;
    }


    @Override
    public void deleteBookingRequest(Long id) {
        repository.save(id);
    }
}
