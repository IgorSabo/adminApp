package rs.odgajivacnica.admin.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.odgajivacnica.admin.business.RoleRepository;
import rs.odgajivacnica.admin.business.UserRepository;
import rs.odgajivacnica.admin.model.entities.Role;
import rs.odgajivacnica.admin.model.entities.User;
import rs.odgajivacnica.admin.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Gile on 8/6/2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
