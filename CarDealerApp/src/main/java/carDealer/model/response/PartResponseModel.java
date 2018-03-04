package carDealer.model.response;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class PartResponseModel {

    private String name;

    private BigDecimal price;

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
