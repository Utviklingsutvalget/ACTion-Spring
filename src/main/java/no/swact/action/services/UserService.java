package no.swact.action.services;

import no.swact.action.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User save(User user);

    User findById(String id);
}
