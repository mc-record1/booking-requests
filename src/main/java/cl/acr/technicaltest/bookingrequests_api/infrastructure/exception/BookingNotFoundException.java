package cl.acr.technicaltest.bookingrequests_api.infrastructure.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long id) {
        super("BookingRequest no encontrado para id: " + id);
    }

    public BookingNotFoundException(String message, Long id) {
        super(message + " " + id);
    }

    public BookingNotFoundException(String message) {
        super(message);
    }
}
