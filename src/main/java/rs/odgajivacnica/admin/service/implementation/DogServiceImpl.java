package rs.odgajivacnica.admin.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.odgajivacnica.admin.business.DogRepository;
import rs.odgajivacnica.admin.model.entities.Dog;
import rs.odgajivacnica.admin.service.DogService;
import rs.odgajivacnica.admin.service.implementation.qdsl.QdslDogRepositoryImpl;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Gile on 8/2/2017.
 */
@Service("dogService")
@Transactional
public class DogServiceImpl implements DogService {

    @Autowired
    DogRepository dogRepository;

    @Autowired
    QdslDogRepositoryImpl qdslDogRepository;

    @Override
    public Dog findById(Long id) {
        return dogRepository.findOne(id);
    }

    @Override
    public List<Dog> getResults(String type, int perPage, int page) {
        return dogRepository.getResults(type, perPage, page);
    }

    @Override
    public Page<Dog> getResults(Pageable page, String gender, String name, Integer age, Boolean isPuppy) {
        return qdslDogRepository.getResults(page, gender, name, age, isPuppy);
    }


}
