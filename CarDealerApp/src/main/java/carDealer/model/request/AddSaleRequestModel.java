package carDealer.model.request;

import javax.validation.constraints.NotNull;

/**
 * Created by George-Lenovo on 03/05/2018.
 */
public class AddSaleRequestModel {

    @NotNull
    private Long customerId;

    @NotNull
    private Long carId;

    @NotNull
    private Double discount;

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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
