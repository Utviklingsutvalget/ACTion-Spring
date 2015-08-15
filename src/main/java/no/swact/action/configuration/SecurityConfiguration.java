package no.swact.action.configuration;

import no.swact.action.authorization.filters.JWTFilter;
import no.swact.action.services.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JWTFilter filter;

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

    public SecurityConfiguration() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                //.headers().cacheControl().and().and()
                .authorizeRequests()
                .filterSecurityInterceptorOncePerRequest(true)
                        // Allow anonymous resource requests
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/views/**").permitAll()

                // Allow anonymous logins
                .antMatchers(HttpMethod.POST, "/authenticate/exchange").permitAll()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()

                .antMatchers(HttpMethod.POST, "/api/**").fullyAuthenticated()
                // All other request need to be authenticated
                .anyRequest().permitAll().and()

                // Custom Token based authentication based on the header previously given to the client
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }
    */

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }
}
