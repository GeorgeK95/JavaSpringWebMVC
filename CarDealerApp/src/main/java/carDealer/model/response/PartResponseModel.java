package carDealer.model.response;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class PartResponseModel {

    private Long id;

    private String name;

    private BigDecimal price;

    private long quantity;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
