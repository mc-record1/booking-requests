package cl.acr.technicaltest.bookingrequests_api.domain.model;

import java.math.BigDecimal;

public class Supplier {

    private Long id;
    private String name;
    private String taxId;
    private Integer country;
    private BigDecimal address;
    private BigDecimal contactEmail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public BigDecimal getAddress() {
        return address;
    }

    public void setAddress(BigDecimal address) {
        this.address = address;
    }

    public BigDecimal getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(BigDecimal contactEmail) {
        this.contactEmail = contactEmail;
    }
}
