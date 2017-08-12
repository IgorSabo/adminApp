package rs.odgajivacnica.admin.model.entities;

import rs.odgajivacnica.admin.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by Gile on 8/7/2017.
 */
@Entity
public class Image extends BaseEntity{


    String imagePath;

    String name;

    Boolean visible = true;

    @ManyToOne(fetch = FetchType.EAGER)
    Dog dog;

    String extension;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
