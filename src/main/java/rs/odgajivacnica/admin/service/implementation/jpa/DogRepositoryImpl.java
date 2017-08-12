package rs.odgajivacnica.admin.service.implementation.jpa;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rs.odgajivacnica.admin.business.DogRepositoryCustom;
import rs.odgajivacnica.admin.model.entities.Dog;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gile on 8/5/2017.
 */
@Repository
@Transactional
public class DogRepositoryImpl implements DogRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Dog> getResults(String type, int perPage, int page) {

        Criteria criteria=null;
        Session session = em.unwrap(Session.class);
        criteria=session.createCriteria(Dog.class);

        if(type != null && type.equals("male")) {
            criteria.add(Restrictions.like("gender", "male"));
        }
        else{
            criteria.add(Restrictions.like("gender", "female"));
        }
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        List<Dog> list = new ArrayList<>();
        list = (ArrayList<Dog>) criteria.list();


        return list;
    }
}
