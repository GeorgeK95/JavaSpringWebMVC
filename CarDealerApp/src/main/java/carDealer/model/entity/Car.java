package carDealer.model.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(precision = 20, nullable = false)
    private Double travelledDistance;

    @ManyToOne
    private Sale sale;

    @ManyToMany()
    @JoinTable(name = "parts_cars", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "part_id", nullable = false)})
    private Set<Part> parts;

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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
