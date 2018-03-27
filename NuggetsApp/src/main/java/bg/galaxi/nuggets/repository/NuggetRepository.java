package bg.galaxi.nuggets.repository;

import bg.galaxi.nuggets.model.entity.Nugget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Repository
public interface NuggetRepository extends JpaRepository<Nugget, Long> {

    @Query(value = "select n.id from nuggets n where n.id in (:ids)", nativeQuery = true)
    List<Nugget> findAllByIds(@Param("ids") String ids);
}
