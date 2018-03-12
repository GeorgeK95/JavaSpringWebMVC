package carDealer.model.request;

/**
 * Created by George-Lenovo on 12/03/2018.
 */
public class AddSaleReviewRequestModel {

    private String customerName;

    private String carMakeModel;

    private Double discount;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarMakeModel() {
        return carMakeModel;
    }

    public void setCarMakeModel(String carMakeModel) {
        this.carMakeModel = carMakeModel;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
