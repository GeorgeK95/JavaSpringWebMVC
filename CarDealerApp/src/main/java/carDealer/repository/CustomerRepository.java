package carDealer.repository;

import carDealer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c\n" +
            "from Customer c\n" +
            "order by c.birthDate asc, c.isYoungDriver desc")
    List<Customer> orderedAscendingCustomers();

    @Query("select c\n" +
            "from Customer c\n" +
            "order by c.birthDate desc, c.isYoungDriver desc")
    List<Customer> orderedDescendingCustomers();

    Customer findByName(String customerName);
}
