package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationProviderImpl.class);

    /**
     * Performs authentication with the same contract as {@link
     * AuthenticationManager#authenticate(Authentication)}.
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return <code>null</code> if the
     * <code>AuthenticationProvider</code> is unable to support authentication of the passed
     * <code>Authentication</code> object. In such a case, the next <code>AuthenticationProvider</code> that
     * supports the presented <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        LOG.debug("Asked to authenticate " + authentication.getPrincipal());
        if (authentication.getPrincipal() == null) {
            LOG.info("Authentication failed");
            return null;
        }

        User one = userRepository.findOne((String) authentication.getPrincipal());
        if (one == null) {
            LOG.warn("User not found");
        }
        return one;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the indicated
     * <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an <code>AuthenticationProvider</code> will be able to
     * authenticate the presented instance of the <code>Authentication</code> class. It simply indicates it can support
     * closer evaluation of it. An <code>AuthenticationProvider</code> can still return <code>null</code> from the
     * {@link #authenticate(Authentication)} method to indicate another <code>AuthenticationProvider</code> should be
     * tried.
     * </p>
     * <p>Selection of an <code>AuthenticationProvider</code> capable of performing authentication is
     * conducted at runtime the <code>ProviderManager</code>.</p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the <code>Authentication</code> class
     * presented
     */
    @Override
    public boolean supports(final Class<?> authentication) {
        boolean assignableFrom = authentication.isAssignableFrom(User.class);
        LOG.debug("Asked if we support " + authentication.getSimpleName() +
                ", which we " + (assignableFrom ? "do" : "don't"));
        return assignableFrom;
    }
}
