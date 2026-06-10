package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.adapter;

import cl.acr.technicaltest.bookingrequests_api.domain.enums.Status;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingItem;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequestUpdate;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingSearchCriteria;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.exception.BookingNotFoundException;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.exception.DatabaseException;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.mapper.BookingRequestEntityMapper;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingItemEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingRequestEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.SupplierEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository.BookingItemJpaRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository.BookingRequestJpaRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository.SupplierJpaRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.specification.BookingRequestSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class BookingRequestRepositoryAdapter implements BookingRequestRepository {

    private final BookingRequestJpaRepository bookingRequestRepository;
    private final BookingItemJpaRepository bookingItemJpaRepository;
    private final SupplierJpaRepository supplierJpaRepository;

    private final BookingRequestEntityMapper mapper;

    public BookingRequestRepositoryAdapter(
            BookingRequestJpaRepository bookingRequestRepository, BookingItemJpaRepository bookingItemJpaRepository, SupplierJpaRepository supplierJpaRepository,
            BookingRequestEntityMapper mapper
    ) {
        this.bookingRequestRepository = bookingRequestRepository;
        this.bookingItemJpaRepository = bookingItemJpaRepository;
        this.supplierJpaRepository = supplierJpaRepository;
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
        if(Objects.isNull(request)){
            throw new BookingNotFoundException("Error al intentar crear el registro de BookingRequest");
        }

        BookingRequestEntity requestEntity = null;
        try{
            requestEntity = mapper.toBookingRequestEntity(request);
        }
        catch (DataAccessException ex){
            throw new DatabaseException("Error al intentar crear el nuevo registro", ex);
        }
        requestEntity.setSupplier(searchSupplier(request.getSupplier().getId()));
        requestEntity.setItems(searchBookingItem(request.getItems()));

        return mapper.toDomain(bookingRequestRepository.saveAndFlush(requestEntity));
    }

    @Override
    public BookingRequest update(BookingRequestUpdate requestUpdate) {

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
                    bookingRequestBd.setStatus(Status.CONFIRMED);
                    break;
                case CONFIRMED:
                    bookingRequestBd.setStatus(Status.CANCELLED);
                    break;
                case CANCELLED:
                    throw new BookingNotFoundException(
                            "La solicitud está cancelada, no se permite transision de estado cuando este esta en CANCELLED",
                            id
                    );
            }

            bookingRequest = mapper.toDomain(bookingRequestRepository.saveAndFlush(bookingRequestBd));

        } catch (DataAccessException e) {
            throw new DatabaseException("Error al intentar actualizar el registro Id: "+ id, e);
        }
        return bookingRequest;
    }

    public SupplierEntity searchSupplier(Long id){
        Optional<SupplierEntity> supplierEntity = supplierJpaRepository.findById(id);
        if(supplierEntity.isEmpty()){
            throw new BookingNotFoundException(
                    "No se puede obtener el proveedor", id);
        }
        return supplierEntity.get();
    }

    public List<BookingItemEntity> searchBookingItem(List<BookingItem> itemsList){
        if(itemsList.isEmpty()){

        }
        return itemsList
                .stream()
                .map(BookingItem::getId)
                .map(id -> bookingItemJpaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "BookingItem no encontrado. Id: " + id
                                )))
                .toList();
    }
}
