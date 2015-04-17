package gainmaster.service.oauth.amqp.gateway;

import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;

/**
 * Created by lorre on 4/16/15.
 */

public class UserRabbitGateway extends RabbitGatewaySupport implements UserGateway {

    protected final static String USER_GET_ROUTING_KEY   = "get";

    public Object getCredentials(String username){
        System.out.println("RABBITMQ: Request credentials for user " + username);
        String password = (String) getRabbitTemplate().convertSendAndReceive(USER_GET_ROUTING_KEY, username);

        if(password == null) return null;
        if(password.isEmpty()) return null;

        return password;
    }
}
