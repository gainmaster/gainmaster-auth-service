package gainmaster.service.oauth;

import gainmaster.service.oauth.amqp.gateway.UserRabbitGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lorre on 4/17/15.
 */

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRabbitGateway userRabbitGateway;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String input_username = authentication.getName();
        String input_password = authentication.getCredentials().toString();

        System.out.println("ATTEMPTING TO AUTHENTICATE USER " + input_username + " WITH PASSWORD " + input_password);

        //Object password = userRabbitGateway.getCredentials(input_username);
        //if(password == null) return null;

        if (input_password.equals("steinar")) {
            List<GrantedAuthority> grantedAuths = new ArrayList();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
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
