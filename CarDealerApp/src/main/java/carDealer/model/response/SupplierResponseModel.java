package carDealer.model.response;

import carDealer.model.Part;

import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
public class SupplierResponseModel {

    private Long id;

    private String name;

    private Set<Part> parts;

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

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
