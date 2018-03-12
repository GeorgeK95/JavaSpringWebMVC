package carDealer.model.response;

/**
 * Created by George-Lenovo on 12/03/2018.
 */
public class CarBasicInfoResponseModel {

    private Long id;

    private String make;

    private String model;

    private Double price;

    private Double finalCarPrice;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFinalCarPrice() {
        return finalCarPrice;
    }

    public void setFinalCarPrice(Double finalCarPrice) {
        this.finalCarPrice = finalCarPrice;
    }
}
