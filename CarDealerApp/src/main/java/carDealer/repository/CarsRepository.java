package carDealer.repository;

import carDealer.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

    @Query("select c\n" +
            "from Car c\n" +
            "where c.make = :make\n" +
            "order by c.model asc, c.travelledDistance desc")
    List<Car> carsFromMake(@Param("make") String make);

}