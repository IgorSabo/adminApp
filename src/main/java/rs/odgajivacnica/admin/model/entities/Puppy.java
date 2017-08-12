package rs.odgajivacnica.admin.model.entities;


import rs.odgajivacnica.admin.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Gile on 8/2/2017.
 */
@Entity
public class Puppy  extends BaseEntity {


    @ManyToOne
    private Dog mother;

    private int age;


}
