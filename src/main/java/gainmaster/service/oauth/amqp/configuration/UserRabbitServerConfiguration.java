package gainmaster.service.oauth.amqp.configuration;

import gainmaster.service.oauth.amqp.handler.CreateUserMessageHandler;
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
public class UserRabbitServerConfiguration extends RabbitServerConfiguration{

    protected final static String USER_EXCHANGE_NAME        = "gainmaster.user.exchange.topic";
    protected final static String USER_QUEUE_NAME           = "gainmaster.service.oauth.user.queue";
    protected final static String USER_CREATE_ROUTING_KEY   = "create";

    @Bean
    public SimpleMessageListenerContainer userListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(USER_QUEUE_NAME);
        container.setMessageListener(new MessageListenerAdapter(new CreateUserMessageHandler()));
        //container.setMessageConverter(jsonMessageConverter());
        return container;
    }

    @Bean
    public MessageConverter jsonMessageConverter() { return new JsonMessageConverter();}

    @Bean
    public Queue userQueue(){ return new Queue(USER_QUEUE_NAME); }

    @Bean
    public TopicExchange userTopicExchange() { return new TopicExchange(USER_EXCHANGE_NAME);}

    @Bean
    public Binding userQueueBinding(){ return BindingBuilder.bind(userQueue()).to(userTopicExchange()).with(USER_CREATE_ROUTING_KEY); }
}
