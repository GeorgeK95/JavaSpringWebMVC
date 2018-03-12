package carDealer.model.request;

/**
 * Created by George-Lenovo on 03/05/2018.
 */
public class AddSaleRequestModel {

    private Long customerId;

    private Long carId;

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
