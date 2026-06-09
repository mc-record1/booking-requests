package cl.acr.technicaltest.bookingrequests_api.application.usecase;

import cl.acr.technicaltest.bookingrequests_api.domain.exceptions.BusinessException;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.port.in.CreateBookingRequestUseCase;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.BookingRequestRepository;
import cl.acr.technicaltest.bookingrequests_api.domain.port.out.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateBookingRequestUseCaseImpl implements CreateBookingRequestUseCase {

    private final BookingRequestRepository repository;
    private final SupplierRepository supplierRepository;

    public CreateBookingRequestUseCaseImpl(BookingRequestRepository repository, SupplierRepository supplierRepository) {
        this.repository = repository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public BookingRequest create(BookingRequest request) {
        validBusinessRuler(request);
        return repository.create(request);

    }

    void validBusinessRuler(BookingRequest request){

        if(Objects.isNull(request)){
            throw new BusinessException("Problemas con el objeto y los valores del registro a crear");
        }

        if(request.getCurrency() == null || request.getIncotermCode() == null){
            throw new BusinessException("Los valores Currency ó IncoTermCode son obligatorios");
        }

        if(request.getItems().isEmpty()){
            throw new BusinessException("El registro de BookingRequest debe tener al menos 1 items para poder crearse");
        }

        if(request.getIssueDate().isAfter(request.getExpirationDate())){
            throw new BusinessException("El valor de IssueDate no puede ser mayor que el valor de ExpirationDate");
        }

        if(request.getSupplier() == null || request.getSupplier().getTaxId() == null){
            throw new BusinessException("El proveedor (Supplier) o su taxId no pueden ser nulos");
        }

        if(!supplierRepository.findBy(request.getSupplier().getId())){
            throw new BusinessException("El proveedor (Supplier) o su taxId no encontraron resultados");
        }
    }
}
