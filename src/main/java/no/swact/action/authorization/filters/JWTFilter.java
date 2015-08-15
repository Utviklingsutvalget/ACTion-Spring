package no.swact.action.authorization.filters;

import no.swact.action.models.User;
import no.swact.action.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.debug("Filtering");

        String stringToken = req.getHeader("x-auth");
        if (stringToken == null) {
            logger.debug("No token");
            //throw new InsufficientAuthenticationException("Authorization header not found");
        } else {
            logger.debug("Getting user");
            User user = jwtTokenService.convert(stringToken);
            logger.debug("Got user");
            Authentication auth = authenticationManager.authenticate(user);
            logger.debug("Got auth");
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.debug("Authorized");
        }
        logger.debug("Filtered");
        chain.doFilter(request, response);
    }
}