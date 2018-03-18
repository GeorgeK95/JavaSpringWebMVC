package residentEvilApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentEvilApp.model.entity.Capital;

import java.util.List;

/**
 * Created by George-Lenovo on 16/03/2018.
 */
@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long> {

    List<Capital> findAll();
}
