package rs.odgajivacnica.admin.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.odgajivacnica.admin.model.entities.Dog;
import rs.odgajivacnica.admin.model.entities.Image;

import java.util.List;

/**
 * Created by Gile on 8/10/2017.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByDog( Dog dog );

}
