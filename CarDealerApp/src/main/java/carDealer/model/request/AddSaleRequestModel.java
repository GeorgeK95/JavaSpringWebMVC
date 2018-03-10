package carDealer.model.request;

import carDealer.model.entity.Customer;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 03/05/2018.
 */
public class AddSaleRequestModel {

    private Long customerId;

    private Long carId;

    private BigDecimal discount;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
