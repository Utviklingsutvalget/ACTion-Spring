package no.swact.action.services;

import no.swact.action.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User save(User user);

    User findById(String id);

    List<User> findAll();

    List<User> findAllAdmins();

    User findByEmail(String s);
}
