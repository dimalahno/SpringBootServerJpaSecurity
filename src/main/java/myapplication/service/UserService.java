package myapplication.service;



import myapplication.model.Role;
import myapplication.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User getUserByUsername(String username);

    User getUserById(Integer id);

    void addUser(User user);

    List<User> getAllUser();

    void deleteUserById(Integer id);

    void updateUser(User user);

    Set<Role> getUserRoles(String username);
}
