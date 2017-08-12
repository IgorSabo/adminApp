package rs.odgajivacnica.admin.service;

import rs.odgajivacnica.admin.model.entities.Dog;
import rs.odgajivacnica.admin.model.entities.Image;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by Gile on 8/10/2017.
 */
public interface ImageService {

    void saveImage(Image image);

    Image findImageById( Long id );

    List<Image> getAllImagesForDog( Dog dog );

    void deleteImage( Long id );

    void deleteImage( Image img );

}
