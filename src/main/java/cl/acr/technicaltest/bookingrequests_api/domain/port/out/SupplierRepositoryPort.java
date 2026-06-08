package cl.acr.technicaltest.bookingrequests_api.domain.port.out;

import cl.acr.technicaltest.bookingrequests_api.domain.model.Supplier;

public interface SupplierRepositoryPort {
    Supplier save(Supplier supplier);

}
