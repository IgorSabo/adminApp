package rs.odgajivacnica.admin.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.odgajivacnica.admin.model.entities.User;

/**
 * Created by Gile on 8/6/2017.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);
}