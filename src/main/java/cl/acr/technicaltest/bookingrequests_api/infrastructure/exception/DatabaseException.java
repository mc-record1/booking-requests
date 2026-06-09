package cl.acr.technicaltest.bookingrequests_api.infrastructure.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
