package carDealer.model.request;

import carDealer.model.response.CustomerResponseModel;

/**
 * Created by George-Lenovo on 03/05/2018.
 */
public class AddSaleRequestModel {
   /* private Long id;

    private CarResponseModel car;*/


    private CustomerResponseModel customer;

    public CustomerResponseModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseModel customer) {
        this.customer = customer;
    }


/*  private BigDecimal discount;

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
*/
}
