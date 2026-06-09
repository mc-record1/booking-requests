package cl.acr.technicaltest.bookingrequests_api.application.usecase;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingSearchCriteria;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.GetBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBookingRequestUseCaseImpl implements GetBookingRequestUseCase {


    private final BookingRequestRepository repository;

    public GetBookingRequestUseCaseImpl(BookingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BookingRequest> findAll(BookingSearchCriteria request) {
        return repository.findAll(request);
    }

    @Override
    public BookingRequest findById(Long id) {
        return repository.findBy(id);
    }
}
