package no.swact.action.repositories;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String role_user);

    List<Role> findByUsersContaining(User user);
}
