package gainmaster.service.oauth.amqp.handler;

/**
 * Created by lorre on 4/15/15.
 */
public class DeleteUserMessageHandler {

    public String handleMessage(String message){
        //TODO: Process messages
        System.out.println("RABBITMQ: Received " + message);
        String reply = "gainmaster-oauth-service: Deleting information about user \n" + message;
        System.out.println("RABBITMQ: Sending reply " + reply);
        return reply;
    }
}
