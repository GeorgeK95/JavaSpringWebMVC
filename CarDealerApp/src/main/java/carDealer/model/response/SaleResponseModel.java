package carDealer.model.response;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class SaleResponseModel {

    private Long id;

    private CarResponseModel car;

    private CustomerResponseModel customer;

    private BigDecimal discount;

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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
