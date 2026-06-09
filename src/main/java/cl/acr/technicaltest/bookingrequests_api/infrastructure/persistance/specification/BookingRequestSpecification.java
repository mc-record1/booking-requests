package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.specification;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingSearchCriteria;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingRequestEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookingRequestSpecification {

    private BookingRequestSpecification() {
    }

    public static Specification<BookingRequestEntity> byCriteria(
            BookingSearchCriteria criteria
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> supplier =
                    root.join("supplier");

            predicates.add(
                    cb.equal(
                            supplier.get("taxId"),
                            criteria.getTaxId()
                    )
            );

            if (criteria.getStatus() != null) {

                predicates.add(
                        cb.equal(
                                root.get("status"),
                                criteria.getStatus()
                        )
                );
            }

            if (criteria.getFreightMode() != null) {

                predicates.add(
                        cb.equal(
                                root.get("freightMode"),
                                criteria.getFreightMode()
                        )
                );
            }

            if (criteria.getDateFrom() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("issueDate"),
                                criteria.getDateFrom()
                        )
                );
            }

            if (criteria.getDateTo() != null) {

                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("issueDate"),
                                criteria.getDateTo()
                        )
                );
            }

            if (criteria.getBookingCode() != null &&
                    !criteria.getBookingCode().isBlank()) {

                predicates.add(
                        cb.equal(
                                root.get("bookingCode"),
                                criteria.getBookingCode()
                        )
                );
            }

            return cb.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}
