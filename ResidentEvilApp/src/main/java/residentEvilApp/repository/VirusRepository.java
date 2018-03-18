package residentEvilApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentEvilApp.model.entity.Virus;

import java.util.List;

/**
 * Created by George-Lenovo on 16/03/2018.
 */
@Repository
public interface VirusRepository extends JpaRepository<Virus, Long> {

    List<Virus> findAll();

    Virus findFirstById(Long id);
}
