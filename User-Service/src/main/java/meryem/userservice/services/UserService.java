package meryem.userservice.services;

import meryem.userservice.entities.Role;
import meryem.userservice.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserByUsername(String username);

    Role addRole(Role role);

    User addRoleToUser(String username, String roleName);

    List<User> findAllUsers();
}
