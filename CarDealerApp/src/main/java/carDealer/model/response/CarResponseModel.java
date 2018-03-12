package carDealer.model.response;

import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class CarResponseModel {

    private Long id;

    private String make;

    private String model;

    private Double travelledDistance;

    private SaleResponseModel sale;

    private Set<PartResponseModel> parts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
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
