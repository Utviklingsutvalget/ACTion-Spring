package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import no.swact.action.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(final Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<User> findUsersWithRole(final String role) {
        Role byName = roleRepository.findByName(role);
        if (byName != null) {
            return byName.getUsers();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Role> saveIfNotExists(final List<Role> roles) {
        List<String> names = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        List<Role> rolesFromDb = roleRepository.findByNameIn(names);

        List<Role> rolesToSave = roles.stream()
                .filter(role -> !rolesFromDb.contains(role))
                .collect(Collectors.toList());

        List<Role> save = roleRepository.save(rolesToSave);
        rolesFromDb.addAll(save);
        return rolesFromDb;
    }

    @Override
    public Role findRoleByName(final String roleName) {
        return roleRepository.findByName(roleName);
    }
}
