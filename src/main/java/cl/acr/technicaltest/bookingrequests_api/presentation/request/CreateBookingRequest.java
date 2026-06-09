package cl.acr.technicaltest.bookingrequests_api.presentation.request;


import cl.acr.technicaltest.bookingrequests_api.domain.enums.FreightMode;
import cl.acr.technicaltest.bookingrequests_api.domain.enums.Incoterm;
import cl.acr.technicaltest.bookingrequests_api.domain.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CreateBookingRequest {

    private Long id;
    private String bookingCode;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String currency;
    private Incoterm incotermCode;
    private FreightMode freightMode;
    private String originCountry;
    private String destinationCountry;
    private BigDecimal fobValue;
    private Status status;
    private LocalDateTime createdAt;
    private Boolean active;
    private CreateSupplier supplier;
    private List<CreateBookingItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Incoterm getIncotermCode() {
        return incotermCode;
    }

    public void setIncotermCode(Incoterm incotermCode) {
        this.incotermCode = incotermCode;
    }

    public FreightMode getFreightMode() {
        return freightMode;
    }

    public void setFreightMode(FreightMode freightMode) {
        this.freightMode = freightMode;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public BigDecimal getFobValue() {
        return fobValue;
    }

    public void setFobValue(BigDecimal fobValue) {
        this.fobValue = fobValue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CreateSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(CreateSupplier supplier) {
        this.supplier = supplier;
    }

    public List<CreateBookingItem> getItems() {
        return items;
    }

    public void setItems(List<CreateBookingItem> items) {
        this.items = items;
    }
}
