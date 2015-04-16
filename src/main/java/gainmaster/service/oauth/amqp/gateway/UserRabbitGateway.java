package gainmaster.service.oauth.amqp.gateway;

import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;

import java.io.IOException;

/**
 * Created by lorre on 4/16/15.
 */

public class UserRabbitGateway extends RabbitGatewaySupport implements UserGateway {

    public Object getUser(int id){
        System.out.println("RABBITMQ - Sending request: get " + id);
        getRabbitTemplate().convertSendAndReceive("get", String.valueOf(id));
        return getRabbitTemplate().receiveAndConvert("gainmaster.service.oauth.user.queue.reply");
    }

    public Object getCredentials(String username){
        System.out.println("RABBITMQ - Request credentials for user " + username);
        getRabbitTemplate().convertSendAndReceive("get", username);
        return getRabbitTemplate().receiveAndConvert("gainmaster.service.oauth.user.queue.reply");
    }
}
