package no.swact.action.authorization;

import no.swact.action.models.User;
import no.swact.action.services.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class RequestInitializerInterceptor implements WebRequestInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(RequestInitializerInterceptor.class);
    @Autowired
    public JwtTokenService tokenService;

    @Override
    public void preHandle(final WebRequest webRequest) throws Exception {
        LOG.info("Pre-handling request");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            securityContext = new SecurityContextImpl();
            SecurityContextHolder.setContext(securityContext);
        }
        String jwt = webRequest.getHeader("x-auth");
        if (jwt == null) {
            LOG.info("No jwt");
            return;
        }
        User user = tokenService.convert(jwt);
        securityContext.setAuthentication(user);
        LOG.info("Updated authentication!");
    }

    @Override
    public void postHandle(final WebRequest webRequest, final ModelMap modelMap) throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            LOG.debug("No context in postHandle");
        } else {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                User user = (User) authentication;
                LOG.debug("Posthandled for " + user.getName());
            } else {
                LOG.debug("No authentication in postHandle");
            }
        }
    }

    @Override
    public void afterCompletion(final WebRequest webRequest, final Exception e) throws Exception {

    }
}
