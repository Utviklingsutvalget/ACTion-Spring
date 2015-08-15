package no.swact.action.authorization;

import no.swact.action.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

public class CustomAffirmativeBased implements AccessDecisionManager {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAffirmativeBased.class);
    private AccessDecisionManager accessDecisionManager;
    private List<AccessDecisionVoter<Object>> accessDecisionVoters;

    public CustomAffirmativeBased(final AccessDecisionManager accessDecisionManager,
                                  final List<AccessDecisionVoter<Object>> accessDecisionVoters) {
        this.accessDecisionManager = accessDecisionManager;
        this.accessDecisionVoters = accessDecisionVoters;
    }

    /**
     * Resolves an access control decision for the passed parameters.
     *
     * @param authentication   the caller invoking the method (not null)
     * @param object           the secured object being called
     * @param configAttributes the configuration attributes associated with the secured
     *                         object being invoked
     * @throws AccessDeniedException               if access is denied as the authentication does not
     *                                             hold a required authority or ACL privilege
     * @throws InsufficientAuthenticationException if access is denied as the
     *                                             authentication does not provide a sufficient level of trust
     */
    @Override
    public void decide(final Authentication authentication, final Object object,
                       final Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (authentication instanceof User) {

            for (final AccessDecisionVoter<Object> accessDecisionVoter : accessDecisionVoters) {
                if (accessDecisionVoter.vote(authentication, object, configAttributes) == AccessDecisionVoter.ACCESS_GRANTED) {
                    return;
                }
            }
        } else {
            LOG.warn("Object is not instance of user");
        }

        accessDecisionManager.decide(authentication, object, configAttributes);
    }

    /**
     * Indicates whether this <code>AccessDecisionManager</code> is able to process
     * authorization requests presented with the passed <code>ConfigAttribute</code>.
     * <p>
     * This allows the <code>AbstractSecurityInterceptor</code> to check every
     * configuration attribute can be consumed by the configured
     * <code>AccessDecisionManager</code> and/or <code>RunAsManager</code> and/or
     * <code>AfterInvocationManager</code>.
     * </p>
     *
     * @param attribute a configuration attribute that has been configured against the
     *                  <code>AbstractSecurityInterceptor</code>
     * @return true if this <code>AccessDecisionManager</code> can support the passed
     * configuration attribute
     */
    @Override
    public boolean supports(final ConfigAttribute attribute) {
        return accessDecisionManager.supports(attribute);
    }

    /**
     * Indicates whether the <code>AccessDecisionManager</code> implementation is able to
     * provide access control decisions for the indicated secured object type.
     *
     * @param clazz the class that is being queried
     * @return <code>true</code> if the implementation can process the indicated class
     */
    @Override
    public boolean supports(final Class<?> clazz) {
        return accessDecisionManager.supports(clazz);
    }
}
