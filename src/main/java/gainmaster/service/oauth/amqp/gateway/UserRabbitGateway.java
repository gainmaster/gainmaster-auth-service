package gainmaster.service.oauth.amqp.gateway;

import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;

/**
 * Created by lorre on 4/16/15.
 */

public class UserRabbitGateway extends RabbitGatewaySupport implements UserGateway {

    public Object getCredentials(String username){
        System.out.println("RABBITMQ: Request credentials for user " + username);
        Object password = getRabbitTemplate().convertSendAndReceive("get", username);
        if(password != null){
            if(password.toString().isEmpty()) return null;
        }
        return password;
    }
}
