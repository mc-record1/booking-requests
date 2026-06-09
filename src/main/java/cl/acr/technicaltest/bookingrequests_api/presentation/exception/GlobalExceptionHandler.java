package cl.acr.technicaltest.bookingrequests_api.presentation.exception;

import cl.acr.technicaltest.bookingrequests_api.domain.exceptions.BusinessException;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            EntityNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(
                        "BOOKINGREQUEST_NOT_FOUND",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusiness(
            BusinessException ex) {

        return ResponseEntity.badRequest()
                .body(new ApiErrorResponse(
                        "BUSINESS_ERROR",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabase(
            DatabaseException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ApiErrorResponse(
                                "DATABASE_ERROR",
                                ex.getMessage()
                        )
                );
    }
}
