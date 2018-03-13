package carDealer.model.response;


/**
 * Created by George-Lenovo on 12/03/2018.
 */
public class SaleInfoResponseModel {
    public static final int YOUNG_DRIVER_DISCOUNT_PERCENTAGE = 5;

    private CustomerBasicInfoResponseModel customer;

    private CarBasicInfoResponseModel car;

    private Double discount;

    public SaleInfoResponseModel(CustomerBasicInfoResponseModel customer, CarBasicInfoResponseModel car, Double discount) {
        this.customer = customer;
        this.car = car;
        this.setDiscount(discount);
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
//        if (this.customer.isYoungDriver()) this.discount += YOUNG_DRIVER_DISCOUNT_PERCENTAGE;
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
