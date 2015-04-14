package gainmaster.service.oauth.amqp.handler;

/**
 * Created by lorre on 4/14/15.
 */
public class CreateUserMessageHandler {

    public String handleMessage(String message){

        //TODO: Process messages
        System.out.println("RABBITMQ: Received " + message);
        String reply = "Yeah buddy!";
        System.out.println("RABBITMQ: Sending reply " + reply);
        return reply;
    }
}
