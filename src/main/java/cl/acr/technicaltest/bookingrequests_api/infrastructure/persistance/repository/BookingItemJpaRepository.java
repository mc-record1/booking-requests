package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository;

import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingItemEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingItemJpaRepository extends JpaRepository<BookingItemEntity, Long> {
}
