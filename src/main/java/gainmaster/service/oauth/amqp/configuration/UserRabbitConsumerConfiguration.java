package gainmaster.service.oauth.amqp.configuration;

import gainmaster.service.oauth.amqp.handler.CreateUserMessageHandler;
import gainmaster.service.oauth.amqp.handler.DeleteUserMessageHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lorre on 4/13/15.
 */

@Configuration
public class UserRabbitConsumerConfiguration extends RabbitServerConfiguration{

    protected final static String USER_CREATE_ROUTING_KEY   = "create";
    protected final static String USER_DELETE_ROUTING_KEY   = "delete";

    @Bean
    public SimpleMessageListenerContainer createUserListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(createUserQueue());
        container.setMessageListener(new MessageListenerAdapter(new CreateUserMessageHandler()));
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer deleteUserListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(deleteUserQueue());
        container.setMessageListener(new MessageListenerAdapter(new DeleteUserMessageHandler()));
        return container;
    }

    @Bean
    public Queue createUserQueue(){ return amqpAdmin().declareQueue(); }

    @Bean
    public Queue deleteUserQueue(){ return amqpAdmin().declareQueue(); }

    @Bean
    public Binding createUserQueueBinding(){ return BindingBuilder.bind(createUserQueue()).to(userTopicExchange()).with(USER_CREATE_ROUTING_KEY); }

    @Bean
    public Binding deleteUserQueueBinding(){ return BindingBuilder.bind(deleteUserQueue()).to(userTopicExchange()).with(USER_DELETE_ROUTING_KEY); }
}
