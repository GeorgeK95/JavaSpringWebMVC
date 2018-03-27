package bg.galaxy.nuggetsconsumer.repository;

import bg.galaxy.nuggetsconsumer.model.entity.Nugget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Repository
public interface NuggetRepository extends JpaRepository<Nugget, String> {

    @Query(value = "SELECT n.id FROM nuggets n WHERE n.name REGEXP :nameList", nativeQuery = true)
    List<String> findByName(@Param("nameList") String names);

}
