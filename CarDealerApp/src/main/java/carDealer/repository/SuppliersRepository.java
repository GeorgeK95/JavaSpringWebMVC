package carDealer.repository;

import carDealer.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByIsImportedTrue();

    List<Supplier> findAllByIsImportedFalse();
}
