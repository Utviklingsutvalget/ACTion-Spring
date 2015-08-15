package no.swact.action.services;

import no.swact.action.models.auth.Role;
import no.swact.action.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(final Role admin) {
        if (admin.getId() != null) {
            throw new RuntimeException("Role had ID set: use update instead!");
        } else {
            return roleRepository.save(admin);
        }
    }
}
