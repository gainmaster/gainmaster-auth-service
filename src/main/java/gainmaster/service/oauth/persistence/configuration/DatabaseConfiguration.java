package gainmaster.service.oauth.persistence.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean(name = "oauthDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.oauth")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }
}
