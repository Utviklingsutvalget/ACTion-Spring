package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    Role save(Role admin);

    List<User> findByName(String s);
}
