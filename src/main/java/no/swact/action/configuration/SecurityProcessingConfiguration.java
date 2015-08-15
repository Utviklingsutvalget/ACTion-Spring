package no.swact.action.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityProcessingConfiguration extends GlobalMethodSecurityConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityProcessingConfiguration.class);

    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        return new DefaultMethodSecurityExpressionHandler();
    }

}
