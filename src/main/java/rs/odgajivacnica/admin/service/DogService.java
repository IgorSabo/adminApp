package rs.odgajivacnica.admin.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.odgajivacnica.admin.model.entities.Dog;

import java.util.List;

/**
 * Created by Gile on 8/2/2017.
 */
public interface DogService {

    Dog findById(Long id);

    List<Dog> getResults(String type, int perPage, int page);

    Page<Dog> getResults(Pageable page, String type, String name, Integer age, Boolean isPuppy);

    //public List<Puppy> findPuppies
}
