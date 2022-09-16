package demo.automat.repositories;

import demo.automat.entities.Getraenk;
import demo.automat.entities.Muenze;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetraenkeRepository extends CrudRepository<Getraenk,Long> {
}
