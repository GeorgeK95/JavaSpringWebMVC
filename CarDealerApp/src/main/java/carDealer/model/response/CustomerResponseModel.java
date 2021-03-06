package carDealer.model.response;

import java.util.Date;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class CustomerResponseModel {

    private Long id;

    private String name;

    private Date birthDate;

    private boolean isYoungDriver;

    private Set<SaleResponseModel> sales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleResponseModel> getSales() {
        return sales;
    }

    public void setSales(Set<SaleResponseModel> sales) {
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
