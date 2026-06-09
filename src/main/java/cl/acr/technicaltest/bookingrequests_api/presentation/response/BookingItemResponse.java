package cl.acr.technicaltest.bookingrequests_api.presentation.response;

import java.math.BigDecimal;

public class BookingItemResponse {

    private Long id;
    private String sku;
    private String description;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalMount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalMount() {
        return totalMount;
    }

    public void setTotalMount(BigDecimal totalMount) {
        this.totalMount = totalMount;
    }
}
