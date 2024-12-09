package meryem.userservice.services;

import jakarta.transaction.Transactional;
import meryem.userservice.entities.Role;
import meryem.userservice.entities.User;
import meryem.userservice.respositories.RoleRepository;
import meryem.userservice.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public User addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRole(rolename);

        user.getRoles().add(role);
        return user;
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}

