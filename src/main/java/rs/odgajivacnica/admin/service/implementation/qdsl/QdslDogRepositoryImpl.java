package rs.odgajivacnica.admin.service.implementation.qdsl;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import rs.odgajivacnica.admin.model.entities.Dog;
import rs.odgajivacnica.admin.model.entities.QDog;

/**
 * Created by Gile on 8/6/2017.
 */
@Repository
public class QdslDogRepositoryImpl extends QuerydslBaseRepoSupport<Dog> {

    public QdslDogRepositoryImpl() {
        super(Dog.class);
    }

    public Page<Dog> getResults(Pageable pageable, String gender, String name, Integer age, Boolean isPuppy){
        QDog qDog = QDog.dog;
        JPQLQuery<Dog> query = from(QDog.dog);

        if(gender != null && !gender.isEmpty() && !gender.equals("All")) {
            query.where(qDog.gender.like(gender));
        }

        if( name != null && !name.isEmpty()){
            query.where(qDog.name.like("%"+name+"%"));
        }

        if( age != null ){
            query.where(qDog.age.eq(age));
        }

        if( isPuppy != null ){
            query.where(qDog.isPuppy.eq(true));
        }

        getQuerydsl().applyPagination(pageable, query);

        Page<Dog> page = new PageImpl<Dog>(query.fetch(), pageable, query.fetchCount());
        return page;
    }
}
