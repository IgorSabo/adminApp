package rs.odgajivacnica.admin.business;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.odgajivacnica.admin.model.entities.Dog;


/**
 * Created by Gile on 8/2/2017.
 */
@Repository
public interface DogRepository extends CrudRepository<Dog, Long>, DogRepositoryCustom {
}
