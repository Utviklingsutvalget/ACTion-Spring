package no.swact.action.services;

import no.swact.action.models.auth.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role save(Role admin);
}
