package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(final User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(final String id) {
        return userRepository.findOne(id);
    }

}
