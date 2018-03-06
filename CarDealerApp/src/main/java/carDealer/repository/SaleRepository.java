package carDealer.repository;

import carDealer.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("select s from Sale s where s.customer.id =:customerId")
    List<Sale> totalSalesByCustomer(@Param("customerId") Long id);

    @Query("select s from Sale s where s.discount > 0.00")
    List<Sale> findAllDiscounted();

    @Query("select s from Sale s where s.discount = :percent")
    List<Sale> findAllDiscountedByPercent(@Param("percent") BigDecimal percent);
}
