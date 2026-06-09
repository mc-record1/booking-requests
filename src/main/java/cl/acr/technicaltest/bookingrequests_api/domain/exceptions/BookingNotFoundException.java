package cl.acr.technicaltest.bookingrequests_api.domain.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
