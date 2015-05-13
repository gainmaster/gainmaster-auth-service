package gainmaster.service.oauth.amqp.gateway;

import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;

/**
 * Created by lorre on 4/16/15.
 */

public class UserRabbitGateway extends RabbitGatewaySupport implements UserGateway {

    protected final static String USER_AUTHENTICATION_ROUTING_KEY = "authentication";

    public boolean authenticate(String username, String password){
        System.out.println("RABBITMQ: Request credentials for user " + username);
        String response = (String) getRabbitTemplate().convertSendAndReceive(USER_AUTHENTICATION_ROUTING_KEY, username + ":" + password);

        //Response is null if no answer from bus
        if(response == null)                    return false;

        //Return true if response equals true
        return response.equalsIgnoreCase("true");
    }
}
