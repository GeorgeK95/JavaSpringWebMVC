package carDealer.model.response;


/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class SaleResponseModel {

    private Long id;

    private CarResponseModel car;

    private CustomerResponseModel customer;

    private Double discount;

    public CarResponseModel getCar() {
        return car;
    }

    public void setCar(CarResponseModel car) {
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerResponseModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseModel customer) {
        this.customer = customer;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
