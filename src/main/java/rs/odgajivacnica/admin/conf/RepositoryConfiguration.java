package rs.odgajivacnica.admin.conf;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"rs.odgajivacnica.admin.model.entities"})
@EnableJpaRepositories(basePackages = {"rs.odgajivacnica.admin.business"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
