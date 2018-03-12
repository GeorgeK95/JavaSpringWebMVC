package carDealer.model.response;


/**
 * Created by George-Lenovo on 12/03/2018.
 */
public class SaleInfoResponseModel {

    private CustomerBasicInfoResponseModel customer;

    private CarBasicInfoResponseModel car;

    private Double discount;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CustomerBasicInfoResponseModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBasicInfoResponseModel customer) {
        this.customer = customer;
    }

    public CarBasicInfoResponseModel getCar() {
        return car;
    }

    public void setCar(CarBasicInfoResponseModel car) {
        this.car = car;
    }
}
