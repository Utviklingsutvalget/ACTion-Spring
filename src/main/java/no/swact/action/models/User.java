package no.swact.action.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.services.oauth2.model.Userinfoplus;
import no.swact.action.models.auth.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements Authentication {

    public static final String EMAIL_SUFFIX = "@student.westerdals.no";
    @Id
    private String id;

    private String pictureUrl;

    private String givenName;

    private String familyName;

    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Transient
    @JsonIgnore
    private String accessToken;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    public User() {

    }

    public User(Userinfoplus userInfo, String accessToken) {
        this.accessToken = accessToken;
        this.pictureUrl = userInfo.getPicture();
        this.email = userInfo.getEmail();
        this.givenName = userInfo.getGivenName();
        this.id = userInfo.getId();
        this.name = userInfo.getName();
        this.familyName = userInfo.getFamilyName();

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(final String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public Object getCredentials() {
        return this.accessToken;
    }

    @JsonIgnore
    @Override
    public Object getDetails() {
        return null;
    }

    @JsonIgnore
    @Override
    public Object getPrincipal() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @JsonIgnore
    @Override
    public void setAuthenticated(final boolean b) throws IllegalArgumentException {

    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}
