package rs.odgajivacnica.admin.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.odgajivacnica.admin.model.entities.Role;

/**
 * Created by Gile on 8/6/2017.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}

