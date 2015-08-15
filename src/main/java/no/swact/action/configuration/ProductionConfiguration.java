package no.swact.action.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;

@Configuration
@Profile({
        "!dev",
        "!test",
        "prod"
})
public class ProductionConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driver = environment.getProperty("spring.datasource.driver-class-name");
        Assert.notNull(driver, "Property \"spring.datasource.driver-class-name\" must not be null!");
        dataSource.setDriverClassName(driver);
        String property = environment.getProperty("spring.datasource.url");
        Assert.notNull(property, "Property \"spring.datasource.url\" must not be null!");
        dataSource.setUrl(property);
        String username = environment.getProperty("spring.datasource.username");
        Assert.notNull(username, "Property \"spring.datasource.username\" must not be null!");
        dataSource.setUsername(username);
        String password = environment.getProperty("spring.datasource.pass");
        Assert.notNull(username, "Property \"spring.datasource.pass\" must not be null!");
        dataSource.setPassword(password);

        return dataSource;
    }
}
