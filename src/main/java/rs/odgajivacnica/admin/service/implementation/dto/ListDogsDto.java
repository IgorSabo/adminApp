package rs.odgajivacnica.admin.service.implementation.dto;

import org.springframework.data.domain.Page;
import rs.odgajivacnica.admin.model.entities.Dog;

/**
 * Created by Gile on 8/7/2017.
 */
public class ListDogsDto {

    Page<Dog> list;
    String gender;
    String name;
    Integer age;
    Boolean isPuppy;

    public Page<Dog> getList() {
        return list;
    }

    public void setList(Page<Dog> list) {
        this.list = list;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsPuppy() {
        return isPuppy;
    }

    public void setIsPuppy(Boolean puppy) {
        isPuppy = puppy;
    }
}
