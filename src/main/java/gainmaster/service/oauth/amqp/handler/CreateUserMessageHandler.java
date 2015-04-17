package gainmaster.service.oauth.amqp.handler;

import org.springframework.stereotype.Component;

/**
 * Created by lorre on 4/14/15.
 */

@Component
public class CreateUserMessageHandler {

    public void handleMessage(String message){
        //TODO: Process messages
        System.out.println("RABBITMQ: Received " + message);
        String reply = "gainmaster-oauth-service: Creating information about user \n" + message;
        //System.out.println("RABBITMQ: Sending reply " + reply);
        //return reply;
    }
}
