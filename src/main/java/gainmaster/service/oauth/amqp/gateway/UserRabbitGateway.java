package gainmaster.service.oauth.amqp.gateway;

import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by lorre on 4/16/15.
 */

public class UserRabbitGateway extends RabbitGatewaySupport implements UserGateway {

    public Object getCredentials(String username){
        System.out.println("RABBITMQ: Request credentials for user " + username);
        return getRabbitTemplate().convertSendAndReceive("get", username);
    }
}
