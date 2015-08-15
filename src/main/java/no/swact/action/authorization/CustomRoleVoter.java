package no.swact.action.authorization;

import no.swact.action.models.User;
import no.swact.action.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomRoleVoter implements AccessDecisionVoter<User> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRoleVoter.class);
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(final ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return aClass.isAssignableFrom(User.class);
    }

    @Override
    public int vote(final Authentication authentication, final User user, final Collection<ConfigAttribute> collection) {
        if(user == null || authentication == null) {
            LOG.info("Not logged in, denying access");
            return -1;
        }

        System.out.println(collection);

        User fromDb = userService.findById(user.getId());
        Collection<GrantedAuthority> authorities = fromDb.getAuthorities();

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        return 0;
    }
}
