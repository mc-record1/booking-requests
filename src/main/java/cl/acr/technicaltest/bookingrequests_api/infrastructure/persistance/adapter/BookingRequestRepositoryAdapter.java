package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.adapter;

import cl.acr.technicaltest.bookingrequests_api.domain.enums.Status;
import cl.acr.technicaltest.bookingrequests_api.domain.exceptions.BusinessException;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingSearchCriteria;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.exception.BookingNotFoundException;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.exception.DatabaseException;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.mapper.BookingRequestEntityMapper;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingRequestEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository.BookingRequestJpaRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.specification.BookingRequestSpecification;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class BookingRequestRepositoryAdapter implements BookingRequestRepository {

    private final BookingRequestJpaRepository bookingRequestRepository;

    private final BookingRequestEntityMapper mapper;

    public BookingRequestRepositoryAdapter(
            BookingRequestJpaRepository bookingRequestRepository,
            BookingRequestEntityMapper mapper
    ) {
        this.bookingRequestRepository = bookingRequestRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookingRequest> findAll(
            BookingSearchCriteria criteria
    ) {
        try{
            var specification = BookingRequestSpecification.byCriteria(criteria);

            return bookingRequestRepository
                    .findAll(specification)
                    .stream()
                    .map(mapper::toDomain)
                    .toList();
        }
        catch (DataAccessException ex){
            throw new DatabaseException("Error al consultar los BookingRequests: ", ex);
        }
    }

    @Override
    public BookingRequest findBy(Long id) {
        return bookingRequestRepository
                .findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public void save(Long id) {
        try{
            Optional<BookingRequestEntity> requestEntity = bookingRequestRepository.findById(id);
            if(requestEntity.isEmpty()){
                throw new BookingNotFoundException(id);
            }

            BookingRequestEntity bookingRequestEntity = requestEntity.get();

            if(bookingRequestEntity.getStatus() == Status.DRAFT
                    || requestEntity.get().getStatus() == Status.CANCELLED) {
                requestEntity.get().setActive(false);
                bookingRequestRepository.saveAndFlush(bookingRequestEntity);
            }
        } catch (DataAccessException e) {
            throw new DatabaseException("Error al intentar eliminar el registro Id: "+ id, e);
        }
    }

    @Override
    public BookingRequest create(BookingRequest request) {
        BookingRequestEntity requestEntity = null;
        if(Objects.isNull(request)){
            throw new BookingNotFoundException("Error al intentar crear el registro de BookingRequest");
        }

        try{
            requestEntity = mapper.toBookingRequestEntity(request);
        }
        catch (DataAccessException ex){
            throw new DatabaseException("Error al intentar crear el nuevo registro", ex);
        }


        return mapper.toDomain(bookingRequestRepository.saveAndFlush(requestEntity));
    }

    @Override
    public BookingRequest update(BookingRequest requestUpdate) {

        BookingRequest bookingRequest = null;

        try{
            Optional<BookingRequestEntity> requestEntity = bookingRequestRepository.findById(requestUpdate.getId());
            if(requestEntity.isEmpty()){
                throw new BookingNotFoundException(requestUpdate.getId());
            }

            BookingRequestEntity bookingRequestBd = requestEntity.get();

            if(bookingRequestBd.getStatus() == Status.DRAFT) {
                bookingRequestBd.setIssueDate(requestUpdate.getIssueDate() == bookingRequestBd.getIssueDate() ?
                                bookingRequestBd.getIssueDate() :
                                requestUpdate.getIssueDate());

                bookingRequestBd.setExpirationDate(requestUpdate.getExpirationDate() == bookingRequestBd.getExpirationDate() ?
                        bookingRequestBd.getExpirationDate() :
                        requestUpdate.getExpirationDate());

                bookingRequestBd.setFobValue(Objects.equals(requestUpdate.getFobValue(), bookingRequestBd.getFobValue()) ?
                        bookingRequestBd.getFobValue() :
                        requestUpdate.getFobValue());

                bookingRequestBd.setCurrency(requestUpdate.getCurrency());

                bookingRequest = mapper.toDomain(bookingRequestRepository.saveAndFlush(bookingRequestBd));
            }
        } catch (DataAccessException e) {
            throw new DatabaseException("Error al intentar actualizar el registro Id: "+ requestUpdate.getId(), e);
        }
        return bookingRequest;
    }

    @Override
    public BookingRequest updateStatus(Long id) {
        BookingRequest bookingRequest = null;

        try{
            Optional<BookingRequestEntity> requestEntity = bookingRequestRepository.findById(id);
            if(requestEntity.isEmpty()){
                throw new BookingNotFoundException(id);
            }

            BookingRequestEntity bookingRequestBd = requestEntity.get();

            switch (bookingRequestBd.getStatus()){
                case DRAFT:
                    bookingRequestBd.setStatus(Status.CANCELLED);
                    break;
                case CONFIRMED:
                    bookingRequestBd.setStatus(Status.CONFIRMED);
                    break;
                case CANCELLED:
                    throw new BookingNotFoundException(
                            "La solicitud está cancelada, no se permite transision de estado cuando este esta en CANCELLED",
                            id
                    );
            }
            bookingRequest = mapper.toDomain(bookingRequestBd);

        } catch (DataAccessException e) {
            throw new DatabaseException("Error al intentar actualizar el registro Id: "+ id, e);
        }
        return bookingRequest;
    }
}
