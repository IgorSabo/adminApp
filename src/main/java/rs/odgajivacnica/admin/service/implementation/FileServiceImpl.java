package rs.odgajivacnica.admin.service.implementation;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.odgajivacnica.admin.model.UploadedFile;
import rs.odgajivacnica.admin.service.FileService;

import java.io.File;
import java.io.IOException;

/**
 * Created by Gile on 8/10/2017.
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Value("${dogs.images.location}")
    String fileLocation;

    @Override
    public boolean deleteFromFileSystem(File f) {

        try{
            return f.delete();
        }
        catch( Exception e){
            //TODO log message
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean saveToFileSystem(UploadedFile uploadedFile) throws IOException {

        File finalFile = new File(uploadedFile.getSaveLocation(), uploadedFile.getName());
        Files.createParentDirs(finalFile);
        Files.write(uploadedFile.getBytes(), finalFile);

        return true;
    }
}
