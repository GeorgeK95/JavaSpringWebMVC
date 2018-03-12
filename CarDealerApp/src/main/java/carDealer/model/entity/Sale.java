package carDealer.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(nullable = false)
    private Double discount;

    @OneToMany(mappedBy = "sale")
    private Set<Car> cars;

    @ManyToOne
    private Customer customer;

    public Sale() {
    }

    public Sale(Double discount, Car car, Customer customer) {
        this.discount = discount;
        this.cars = new HashSet<>();
        this.addCar(car);
        this.customer = customer;
    }

    private void addCar(Car car) {
        this.cars.add(car);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
