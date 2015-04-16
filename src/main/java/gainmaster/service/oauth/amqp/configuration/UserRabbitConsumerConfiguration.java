package gainmaster.service.oauth.amqp.configuration;

import gainmaster.service.oauth.amqp.handler.CreateUserMessageHandler;
import gainmaster.service.oauth.amqp.handler.DeleteUserMessageHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lorre on 4/13/15.
 */

@Configuration
public class UserRabbitConsumerConfiguration extends RabbitServerConfiguration{

    protected final static String USER_QUEUE_CREATE_NAME    = "gainmaster.service.oauth.user.create.queue";
    protected final static String USER_QUEUE_DELETE_NAME    = "gainmaster.service.oauth.user.delete.queue";

    protected final static String USER_CREATE_ROUTING_KEY   = "create";
    protected final static String USER_DELETE_ROUTING_KEY   = "delete";

    @Bean
    public SimpleMessageListenerContainer createUserListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(USER_QUEUE_CREATE_NAME);
        container.setMessageListener(new MessageListenerAdapter(new CreateUserMessageHandler()));
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer deleteUserListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(USER_QUEUE_DELETE_NAME);
        container.setMessageListener(new MessageListenerAdapter(new DeleteUserMessageHandler()));
        return container;
    }

    @Bean
    public Queue createUserQueue(){ return new Queue(USER_QUEUE_CREATE_NAME); }

    @Bean
    public Queue deleteUserQueue(){ return new Queue(USER_QUEUE_DELETE_NAME); }

    @Bean
    public Binding createUserQueueBinding(){ return BindingBuilder.bind(createUserQueue()).to(userTopicExchange()).with(USER_CREATE_ROUTING_KEY); }

    @Bean
    public Binding deleteUserQueueBinding(){ return BindingBuilder.bind(deleteUserQueue()).to(userTopicExchange()).with(USER_DELETE_ROUTING_KEY); }
}
