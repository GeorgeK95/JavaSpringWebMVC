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

//    @Query(value = "SELECT n FROM Nugget n WHERE n.id IN (:ids)")
    List<Nugget> findAllByIdIn(String ids);
//    List<Nugget> findAllByIds(@Param("ids") String ids);
}
