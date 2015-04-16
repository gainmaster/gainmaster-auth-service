package gainmaster.service.oauth;

import gainmaster.service.oauth.amqp.gateway.UserRabbitGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
public class UserAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserRabbitGateway userRabbitGateway;

    private class Auth implements AuthenticationProvider {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            String input_username = authentication.getName();
            String input_password = authentication.getCredentials().toString();

            Object password = userRabbitGateway.getCredentials(input_username);

            //No answer from bus
            if(password == null) return null;
            //No user found
            if(((String) password).isEmpty()) return null;

            if (input_password.equals(password.toString())) {
                List<GrantedAuthority> grantedAuths = new ArrayList();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                Authentication auth = new UsernamePasswordAuthenticationToken(input_username, input_password, grantedAuths);
                return auth;
            } else {
                return null;
            }
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }
    }

    @Override
    public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(new Auth());
        //authenticationManagerBuilder.inMemoryAuthentication()
        //    .withUser("username").password("password").roles("USER");
    }

}
