package no.swact.action.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.jackson2.JacksonFactory;
import no.swact.action.services.ImageUploadService;
import no.swact.action.services.S3ImageUploadService;
import org.jose4j.keys.AesKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "no.swact.action.repositories")
public class ACTionConfiguration extends SpringDataWebConfiguration {

    public static final String AWS_S3_BUCKET = "aws.s3.bucket";
    public static final String AWS_ACCESS_KEY = "aws.access.key";
    public static final String AWS_SECRET_KEY = "aws.secret.key";
    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
        registry.addResourceHandler("/views/**")
                .addResourceLocations("classpath:/views/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/views/index.html")
                        //.setCachePeriod(cachePeriod)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath,
                                                   Resource location) throws IOException {
                        return location.exists() && location.isReadable() ? location
                                : null;
                    }
                });
    }

    @Bean
    public ImageUploadService fileUploadService() {
        String accessKey = env.getProperty(AWS_ACCESS_KEY);
        String secretKey = env.getProperty(AWS_SECRET_KEY);
        final String s3Bucket = env.getProperty(AWS_S3_BUCKET);

        return new S3ImageUploadService(accessKey, secretKey, s3Bucket);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        objectMapper.registerModule(new Hibernate4Module());
        return objectMapper;
    }

    @Bean
    public Key secretKey() throws UnsupportedEncodingException {
        String secret = env.getProperty("action.secret");
        Assert.notNull(secret, "Application secret \"action.secret\" must not be null");
        return new AesKey(secret.getBytes("UTF-8"));
    }

    @Bean
    public GoogleCredential googleCredential() {
        GoogleCredential.Builder credential = new GoogleCredential.Builder();

        String googleClientId = env.getProperty("google.client.id");
        Assert.notNull(googleClientId, "Client ID \"google.client.id\" must not be null");
        String googleClientSecret = env.getProperty("google.client.secret");
        Assert.notNull(googleClientSecret, "Application Secret \"google.client.secret\" must not be null");
        credential.setClientSecrets(googleClientId, googleClientSecret);
        credential.setJsonFactory(JacksonFactory.getDefaultInstance());

        return credential.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(
                "no.swact.action.models"
        );
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        String hibernateDialect = env.getProperty("hibernate.dialect");
        Assert.notNull(hibernateDialect, "Hibernate needs to know which dialect to use. " +
                "Please set the property \"hibernate.dialect\"");
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }
}
