package demo.automat.repositories;

import demo.automat.entities.Muenze;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuenzenRepository extends CrudRepository<Muenze,Long> {
}
