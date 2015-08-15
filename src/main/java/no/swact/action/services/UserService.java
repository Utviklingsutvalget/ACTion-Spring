package no.swact.action.services;

import no.swact.action.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService, AuthenticationProvider {

    User save(User user);

    User findById(String id);

    List<User> findAll();

    List<User> findAllAdmins();
}
