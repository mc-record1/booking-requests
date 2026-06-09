package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.adapter;

import cl.acr.technicaltest.bookingrequests_api.domain.port.out.SupplierRepository;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository.SupplierJpaRepository;
import org.springframework.stereotype.Component;


@Component
public class SupplierRepositoryAdapter implements SupplierRepository {

    private final SupplierJpaRepository supplierJpaRepository;

    public SupplierRepositoryAdapter(SupplierJpaRepository supplierJpaRepository) {
        this.supplierJpaRepository = supplierJpaRepository;
    }


    @Override
    public Boolean findBy(Long id) {
        return  supplierJpaRepository.findById(id).isPresent();
    }
}
