package cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity;

import cl.acr.technicaltest.bookingrequests_api.domain.enums.FreightMode;
import cl.acr.technicaltest.bookingrequests_api.domain.enums.Incoterm;
import cl.acr.technicaltest.bookingrequests_api.domain.enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookingrequest")
public class BookingRequestEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String bookingCode;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false, length = 5)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Incoterm incotermCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FreightMode freightMode;

    @Column(nullable = false, length = 5)
    private String originCountry;

    @Column(nullable = false, length = 5)
    private String destinationCountry;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal fobValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "supplier_id",
            nullable = false
    )
    private SupplierEntity supplier;

    @OneToMany(
            mappedBy = "bookingRequest",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BookingItemEntity> items = new ArrayList<>();


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

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public List<BookingItemEntity> getItems() {
        return items;
    }

    public void setItems(List<BookingItemEntity> items) {
        this.items = items;
    }
}
