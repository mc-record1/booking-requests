package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.repository;

import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRequestJpaRepository extends JpaRepository<BookingRequestEntity, Long> {
}
