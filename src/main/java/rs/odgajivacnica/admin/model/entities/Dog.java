package rs.odgajivacnica.admin.model.entities;


import org.hibernate.annotations.*;
import rs.odgajivacnica.admin.model.BaseEntity;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Gile on 8/2/2017.
 */



@Entity
public class Dog extends BaseEntity{

    private String gender;

    private String name;

    @Lob
    private String description;

    private boolean hasPuppies;

    @Column(name = "mainImageName")
    private String mainImageName;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "dogId")
    private List<Image> images;

    @OneToMany( mappedBy = "mother", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Puppy> puppies;

    private Long motherId;

    private Long fatherId;

    private boolean isPuppy;

    private int age=0;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasPuppies() {
        return hasPuppies;
    }

    public void setHasPuppies(boolean hasPuppies) {
        this.hasPuppies = hasPuppies;
    }

    public String getMainImageName() {
        return mainImageName;
    }

    public void setMainImageName(String mainImageName) {
        this.mainImageName = mainImageName;
    }

    public List<Puppy> getPuppies() {
        return puppies;
    }

    public void setPuppies(List<Puppy> puppies) {
        this.puppies = puppies;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public boolean isPuppy() {
        return isPuppy;
    }

    public void setPuppy(boolean puppy) {
        isPuppy = puppy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
