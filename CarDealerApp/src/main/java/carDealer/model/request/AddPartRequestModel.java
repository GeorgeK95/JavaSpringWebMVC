package carDealer.model.request;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class AddPartRequestModel {

    private String name;

    private BigDecimal price;

    private long quantity = 1;

    private long supplierId;

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
