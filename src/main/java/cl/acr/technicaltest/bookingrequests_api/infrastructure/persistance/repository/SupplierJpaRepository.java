package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository;

import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJpaRepository extends JpaRepository<SupplierEntity, Long> {
}
