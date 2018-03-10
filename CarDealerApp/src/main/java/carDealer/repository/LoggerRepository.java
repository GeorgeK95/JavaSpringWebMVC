package carDealer.repository;

import carDealer.model.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by George-Lenovo on 10/03/2018.
 */
@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
}
