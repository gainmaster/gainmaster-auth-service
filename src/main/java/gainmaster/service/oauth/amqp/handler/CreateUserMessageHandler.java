package gainmaster.service.oauth.amqp.handler;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * Created by lorre on 4/14/15.
 */

@Component
public class CreateUserMessageHandler {

    public String handleMessage(Object message){
        //TODO: Process messages
        System.out.println(message.getClass());
        System.out.println("RABBITMQ: Received " + message);
        String reply = "gainmaster-oauth-service: Creating information about user \n" + message;
        System.out.println("RABBITMQ: Sending reply " + reply);
        return reply;
    }
}
