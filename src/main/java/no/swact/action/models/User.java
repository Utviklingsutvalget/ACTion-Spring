package no.swact.action.models;

import com.google.api.services.oauth2.model.Userinfoplus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User implements Authentication {

    @Id
    private String id;

    private String pictureUrl;

    private String givenName;

    private String familyName;

    private String name;

    private String email;
    @Transient
    private String accessToken;

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

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return this.accessToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.id;
    }

    @Override
    public boolean isAuthenticated() {
        return accessToken != null;
    }

    @Override
    public void setAuthenticated(final boolean b) throws IllegalArgumentException {

    }
}
