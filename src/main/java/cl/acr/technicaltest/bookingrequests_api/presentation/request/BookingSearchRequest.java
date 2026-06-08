package cl.acr.technicaltest.bookingrequests_api.presentation.request;

import java.time.LocalDate;

public class BookingSearchRequest {

    private String taxId;
    private String status;
    private String freightMode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String bookingCode;


    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFreightMode() {
        return freightMode;
    }

    public void setFreightMode(String freightMode) {
        this.freightMode = freightMode;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }
}
