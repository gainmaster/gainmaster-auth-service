package gainmaster.service.oauth.configuration;

import gainmaster.service.oauth.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class UserAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @Override
    public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(userAuthenticationProvider);

        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("admin")
            .password("admin")
            .roles("ADMIN");
    }
}
