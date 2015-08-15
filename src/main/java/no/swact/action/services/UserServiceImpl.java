package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public User save(final User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(final String id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllAdmins() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> !user.getRoles().isEmpty())
                .filter(user -> user.getRoles()
                        .stream()
                        .filter(role -> role.getName().equals("ADMIN"))
                        .findAny()
                        .isPresent())
                .collect(Collectors.toList());
    }

    @Override
    public User findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

}
