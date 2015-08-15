package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import no.swact.action.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(final Role role) {
        if (role.getId() != null) {
            throw new RuntimeException("Role had ID set: use update instead!");
        } else {
            return roleRepository.save(role);
        }
    }

    @Override
    public List<User> findByName(final String role) {
        Role byName = roleRepository.findByName(role);
        if(byName != null) {
            return byName.getUsers();
        } else {
            return new ArrayList<>();
        }
    }
}
