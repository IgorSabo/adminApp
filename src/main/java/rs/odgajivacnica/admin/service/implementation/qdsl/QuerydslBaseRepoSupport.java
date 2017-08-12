package rs.odgajivacnica.admin.service.implementation.qdsl;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;
import rs.odgajivacnica.admin.model.BaseEntity;

/**
 * Created by Gile on 8/6/2017.
 */
@NoRepositoryBean
public class QuerydslBaseRepoSupport <T extends BaseEntity> extends QueryDslRepositorySupport {

    protected Class<? extends BaseEntity> domainClass;

    public QuerydslBaseRepoSupport(Class<T> domainClass) {
        super(domainClass);
    }

}