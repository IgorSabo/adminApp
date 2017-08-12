package rs.odgajivacnica.admin.service;

import rs.odgajivacnica.admin.model.UploadedFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Gile on 8/10/2017.
 */
public interface FileService {

    boolean deleteFromFileSystem( File f );

    boolean saveToFileSystem(UploadedFile uploadedFile) throws IOException;
}
