package rs.odgajivacnica.admin.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.odgajivacnica.admin.business.ImageRepository;
import rs.odgajivacnica.admin.model.entities.Dog;
import rs.odgajivacnica.admin.model.entities.Image;
import rs.odgajivacnica.admin.service.ImageService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Gile on 8/10/2017.
 */
@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;


    public void saveImage(Image image){

        if( image.getId() != null ){
            Image imgFromDb = imageRepository.findOne( image.getId() );
            imgFromDb.setName( image.getName() );
            imgFromDb.setDog( image.getDog() );
            imgFromDb.setExtension( image.getExtension() );
            imgFromDb.setImagePath( image.getImagePath() );
            imgFromDb.setVisible( image.getVisible() );
            imageRepository.save( imgFromDb );
        }
        else{
            imageRepository.save( image );
        }
    }

    @Override
    public Image findImageById( Long id ){
        return imageRepository.findOne( id );
    }

    @Override
    public List<Image> getAllImagesForDog(Dog dog) {
        return imageRepository.findByDog( dog );
    }

    @Override
    @Transactional
    public void deleteImage(Long id) {
        imageRepository.delete( id );
    }

    @Override
    @Transactional()
    public void deleteImage(Image img) {
        imageRepository.delete(img);
    }
}
