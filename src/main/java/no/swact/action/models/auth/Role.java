package no.swact.action.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import no.swact.action.models.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, updatable = false, length = 20)
    private String name;
    @ManyToMany
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public Role(final String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Role role = (Role) o;

        return name.equals(role.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return "ROLE_" + name;
    }

    public void addUser(final User user) {
        users.add(user);
    }
}
