package cl.acr.technicaltest.bookingrequests_api.domain.model;

import cl.acr.technicaltest.bookingrequests_api.domain.enums.FreightMode;
import cl.acr.technicaltest.bookingrequests_api.domain.enums.Status;

import java.time.LocalDate;

public class BookingSearchCriteria {
    private String taxId;
    private Status status;
    private FreightMode freightMode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String bookingCode;


    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FreightMode getFreightMode() {
        return freightMode;
    }

    public void setFreightMode(FreightMode freightMode) {
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
