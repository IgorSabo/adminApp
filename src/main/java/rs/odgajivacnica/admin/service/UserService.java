package rs.odgajivacnica.admin.service;

import rs.odgajivacnica.admin.model.entities.User;

/**
 * Created by Gile on 8/6/2017.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);

    /**
     * Created by Gile on 8/10/2017.
     */
    interface ImageService{
    }
}
