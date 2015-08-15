package no.swact.action.models.auth;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;
import java.util.UUID;

public class SecurityKey {

    private String key = UUID.randomUUID().toString();

    @ManyToOne
    private Role role;

    private ZonedDateTime expiresAt = ZonedDateTime.now().plusHours(48);
}
