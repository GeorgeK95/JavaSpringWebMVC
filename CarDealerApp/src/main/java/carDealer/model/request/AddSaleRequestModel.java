package carDealer.model.request;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 03/05/2018.
 */
public class AddSaleRequestModel {

    private AddCustomerRequestModel customer;

    private AddCarRequestModel car;

    private BigDecimal percentage;

    public AddCustomerRequestModel getCustomer() {
        return customer;
    }

    public void setCustomer(AddCustomerRequestModel customer) {
        this.customer = customer;
    }

    public AddCarRequestModel getCar() {
        return car;
    }

    public void setCar(AddCarRequestModel car) {
        this.car = car;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
