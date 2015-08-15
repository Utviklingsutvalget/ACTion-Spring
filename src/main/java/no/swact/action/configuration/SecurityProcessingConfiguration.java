package no.swact.action.configuration;

import no.swact.action.authorization.CustomAffirmativeBased;
import no.swact.action.authorization.voters.SuperAdminRoleVoter;
import no.swact.action.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityProcessingConfiguration extends GlobalMethodSecurityConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityProcessingConfiguration.class);

    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        return new DefaultMethodSecurityExpressionHandler();
    }

    @Override
    protected AccessDecisionManager accessDecisionManager() {

        return new CustomAffirmativeBased(super.accessDecisionManager(), customVoters());
    }

    private List<AccessDecisionVoter<Object>> customVoters() {
        List<AccessDecisionVoter<Object>> voters = new ArrayList<>();
        voters.add(new SuperAdminRoleVoter());
        return voters;
    }
}
