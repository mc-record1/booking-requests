package cl.acr.technicaltest.bookingrequests_api.domain.port.out;

public interface SupplierRepository {
    Boolean findBy(Long id);
}
