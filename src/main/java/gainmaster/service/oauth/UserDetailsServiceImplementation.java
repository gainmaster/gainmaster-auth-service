package gainmaster.service.oauth;

import gainmaster.service.oauth.amqp.gateway.UserRabbitGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lorre on 5/11/15.
 */

@Component
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    UserRabbitGateway userRabbitGateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Object password = userRabbitGateway.getCredentials(username);
        //if(password == null) return null;

        List<GrantedAuthority> grantedAuths = new ArrayList();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));

        return new User(username, "steinar", grantedAuths);
    }

}
