package rs.odgajivacnica.admin.presentation.controllers;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.odgajivacnica.admin.AppConstants;
import rs.odgajivacnica.admin.commons.MessageAwareRedirectView;
import rs.odgajivacnica.admin.model.UploadedFile;
import rs.odgajivacnica.admin.model.entities.Dog;
import rs.odgajivacnica.admin.model.entities.Image;
import rs.odgajivacnica.admin.service.DogService;
import rs.odgajivacnica.admin.service.FileService;
import rs.odgajivacnica.admin.service.ImageService;
import rs.odgajivacnica.admin.service.implementation.dto.ListDogsDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gile on 8/6/2017.
 */
@Controller
public class DogController {

    @Value("${dogs.images.location}")
    String dogImagesLocation;

    @Autowired
    DogService dogService;

    @Autowired
    ImageService imageService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/dog/{dogId}/getMainImage", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMainImageFile(@PathVariable Long dogId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        Dog dog = dogService.findById(dogId);

        File in = new File(dogImagesLocation+"/"+dogId+"/"+dog.getMainImageName());
        byte[] array = null;
        try{
            array = Files.toByteArray(in);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(array, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dog/{dogId}/getStandardImage/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getStandardImageFile(@PathVariable Long dogId, @PathVariable Long imageId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        Dog dog = dogService.findById(dogId);

        List<Image> images = dog.getImages();

        String imgPath="";
        for(Image img : images){
            if( img.getId().equals(imageId) ){
                imgPath = img.getImagePath();
                break;
            }

        }

        File in = new File(imgPath);
        byte[] array = null;
        try{
            array = Files.toByteArray(in);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(array, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dog/{dogId}/deleteImage/{imageId}", method = RequestMethod.POST)
    public MessageAwareRedirectView deleteImage(@PathVariable Long dogId, @PathVariable Long imageId, RedirectAttributes redirectAttribs){

        MessageAwareRedirectView mrv = new MessageAwareRedirectView("/dog/"+dogId+"/edit",redirectAttribs);
        String filePath = "";
        try{
            //delete from db
            Image img = imageService.findImageById( imageId );
            filePath = img.getImagePath();
            imageService.deleteImage(img);


            //delete from file system
            if(fileService.deleteFromFileSystem( new File(filePath))) {
                mrv.addInfo("image.delete.success");
                return mrv;
            }

        }
        catch(Exception e){
            e.printStackTrace();
            mrv.addError("image.delete.error");
        }

        return mrv;
    }

    @RequestMapping(value = "/dog/{dogId}/setImageVisibility/{imageId}", method = RequestMethod.POST)
    public MessageAwareRedirectView setImageVisibility(@PathVariable Long dogId, @PathVariable String imageId, RedirectAttributes redirectAttribs, HttpServletRequest request){

        Boolean visible = null;
        String visibility = request.getParameter("visibility");
        MessageAwareRedirectView mrv = new MessageAwareRedirectView("/dog/"+dogId+"/edit",redirectAttribs);
        Long numericId = null;



        try{

            if( visibility != null ){
                visible = Boolean.valueOf(request.getParameter("visibility"));
            }

            if( imageId != null ){
                String[] parts = imageId.split("currentImageVisible");
                numericId = Long.valueOf(parts[1]);
            }

            //update db record
            Image img = imageService.findImageById( numericId );
            if( visible != null ){
                img.setVisible( visible );
                imageService.saveImage(img);
            }


            mrv.addInfo("image.visibility.updated");

        }
        catch(Exception e){
            e.printStackTrace();
            mrv.addError("image.operation.error");
        }

        return mrv;
    }


    @RequestMapping(value = "/dog/{dogId}/uploadImage/", method = RequestMethod.POST)
    public MessageAwareRedirectView  uploadImage(MultipartHttpServletRequest request, HttpServletResponse response, @PathVariable Long dogId, RedirectAttributes redirectAttribs){

        //1. get the files from the request object
        Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf = request.getFile(itr.next());

        //save to db
        Image newImg = new Image();
        newImg.setVisible( true );
        newImg.setImagePath(dogImagesLocation+"/"+dogId+"/"+mpf.getOriginalFilename());
        newImg.setExtension( "" );
        newImg.setDog( dogService.findById(dogId) );
        newImg.setName(mpf.getOriginalFilename());
        newImg.setVersion( 1 );
        imageService.saveImage( newImg );

        UploadedFile uFile = new UploadedFile();
        MessageAwareRedirectView mrv = new MessageAwareRedirectView("/dog/"+dogId+"/edit",redirectAttribs);
        try {
            //just temporary save file info into a file
            uFile.setLength( mpf.getBytes().length );
            uFile.setBytes ( mpf.getBytes() );
            uFile.setType( mpf.getContentType() );
            uFile.setName ( mpf.getOriginalFilename() );
            uFile.setSaveLocation(dogImagesLocation+"/"+dogId);

            //save file on file system
            System.out.println(mpf.getOriginalFilename() +" uploaded!");


            if (fileService.saveToFileSystem( uFile )){
                mrv.addInfo("image.upload.success");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            mrv.addError("image.upload.error");
            e.printStackTrace();
        }

        return mrv;
    }


    @RequestMapping(value="/dog", method = RequestMethod.GET)
    ModelAndView listEditAllDogs(Pageable page,
                                 @RequestParam(name = "Gender", required = false) String gender,
                                 @RequestParam(name = "Name", required = false) String name,
                                 @RequestParam(name = "Age", required = false) Integer age,
                                 @RequestParam(name = "Puppy", required = false) Boolean isPuppy){
        ModelAndView mv = new ModelAndView("list-edit-dogs");
        Page<Dog> list = dogService.getResults(page, gender, name, age, isPuppy);

        ListDogsDto dto = new ListDogsDto();
        dto.setList(list);
        dto.setName(name);
        dto.setGender(gender);
        dto.setAge(age);
        dto.setIsPuppy(isPuppy);
        mv.addObject("results", dto);

        return mv;
    }

    @RequestMapping(value = "/dog/{dogId}/edit", method = RequestMethod.GET)
    ModelAndView editDogDetails(@PathVariable Long dogId){
        Dog dog = dogService.findById(dogId);
        ModelAndView mv = new ModelAndView("edit-dog");
        mv.addObject("dog", dog);
        return mv;
    }


}
