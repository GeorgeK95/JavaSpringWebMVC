package carDealer.repository;

import carDealer.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query("select c.parts from Car as c where c.id = :id")
    Set<Part> findCarPartsById(@Param("id") Long id);

    @Query("select p from Part as p where p.id in :ids")
    List<Part> findPartsById(@Param("ids") List<Long> ids);

}
