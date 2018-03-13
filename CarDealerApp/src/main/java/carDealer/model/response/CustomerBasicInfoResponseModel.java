package carDealer.model.response;

/**
 * Created by George-Lenovo on 12/03/2018.
 */
public class CustomerBasicInfoResponseModel {

    private Long id;

    private String name;

    private boolean isYoungDriver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
