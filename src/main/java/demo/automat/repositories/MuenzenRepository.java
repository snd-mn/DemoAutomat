package demo.automat.repositories;

import demo.automat.entities.Muenze;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuenzenRepository extends CrudRepository<Muenze,Long> {

    @Query("SELECT m FROM Muenze m ORDER BY m.wert DESC")
    List<Muenze> findAllOrderdByValueDesc();
}
