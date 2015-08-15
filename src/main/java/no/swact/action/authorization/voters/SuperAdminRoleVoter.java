package no.swact.action.authorization.voters;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class SuperAdminRoleVoter implements AccessDecisionVoter<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(SuperAdminRoleVoter.class);

    /**
     * Indicates whether this {@code AccessDecisionVoter} is able to vote on the passed
     * {@code ConfigAttribute}.
     * <p/>
     * This allows the {@code AbstractSecurityInterceptor} to check every configuration
     * attribute can be consumed by the configured {@code AccessDecisionManager} and/or
     * {@code RunAsManager} and/or {@code AfterInvocationManager}.
     *
     * @param attribute a configuration attribute that has been configured against the
     *                  {@code AbstractSecurityInterceptor}
     * @return true if this {@code AccessDecisionVoter} can support the passed
     * configuration attribute
     */
    @Override
    public boolean supports(final ConfigAttribute attribute) {
        return attribute.getAttribute().startsWith("ROLE_");
    }

    /**
     * Indicates whether the {@code AccessDecisionVoter} implementation is able to provide
     * access control votes for the indicated secured object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(final Class<?> clazz) {
        return true;
    }

    /**
     * Indicates whether or not access is granted.
     * <p/>
     * The decision must be affirmative ({@code ACCESS_GRANTED}), negative (
     * {@code ACCESS_DENIED}) or the {@code AccessDecisionVoter} can abstain (
     * {@code ACCESS_ABSTAIN}) from voting. Under no circumstances should implementing
     * classes return any other value. If a weighting of results is desired, this should
     * be handled in a custom
     * {@link AccessDecisionManager} instead.
     * <p/>
     * Unless an {@code AccessDecisionVoter} is specifically intended to vote on an access
     * control decision due to a passed method invocation or configuration attribute
     * parameter, it must return {@code ACCESS_ABSTAIN}. This prevents the coordinating
     * {@code AccessDecisionManager} from counting votes from those
     * {@code AccessDecisionVoter}s without a legitimate interest in the access control
     * decision.
     * <p/>
     * Whilst the secured object (such as a {@code MethodInvocation}) is passed as a
     * parameter to maximise flexibility in making access control decisions, implementing
     * classes should not modify it or cause the represented invocation to take place (for
     * example, by calling {@code MethodInvocation.proceed()}).
     *
     * @param authentication the caller making the invocation
     * @param object         the secured object being invoked
     * @param attributes     the configuration attributes associated with the secured object
     * @return either {@link #ACCESS_GRANTED}, {@link #ACCESS_ABSTAIN} or
     * {@link #ACCESS_DENIED}
     */
    @Override
    public int vote(final Authentication authentication, final Object object, final Collection<ConfigAttribute> attributes) {
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        if(roles.contains(new Role("ADMIN"))) {
            return ACCESS_GRANTED;
        } else {
            return ACCESS_ABSTAIN;
        }
    }
}
