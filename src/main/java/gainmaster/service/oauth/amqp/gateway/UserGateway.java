package gainmaster.service.oauth.amqp.gateway;

/**
 * Created by lorre on 4/16/15.
 */

public interface UserGateway {
    Object getCredentials(String username);
}
