package carDealer.model.request;

import java.util.List;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class AddCarRequestModel {

    private String make;

    private String model;

    private Double travelledDistance;

    private List<Long> selectedParts;

    public List<Long> getSelectedParts() {
        return selectedParts;
    }

    public void setSelectedParts(List<Long> selectedParts) {
        this.selectedParts = selectedParts;
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
}
