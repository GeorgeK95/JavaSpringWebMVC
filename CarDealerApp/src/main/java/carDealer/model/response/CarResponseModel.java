package carDealer.model.response;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class CarResponseModel {

    private String make;

    private String model;

    private BigDecimal travelledDistance;

    private SaleResponseModel sale;

    private Set<PartResponseModel> parts;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(BigDecimal travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartResponseModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartResponseModel> parts) {
        this.parts = parts;
    }

    public SaleResponseModel getSale() {
        return sale;
    }

    public void setSale(SaleResponseModel sale) {
        this.sale = sale;
    }
}
