package gainmaster.service.oauth.amqp.configuration;

import gainmaster.service.oauth.amqp.gateway.UserRabbitGateway;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lorre on 4/16/15.
 */

@Configuration
public class UserRabbitPublisherConfiguration extends RabbitServerConfiguration{

    protected final static String USER_REPLY_QUEUE_NAME = "gainmaster.service.oauth.user.queue.reply";

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange(USER_EXCHANGE_NAME);
        template.setReplyQueue(userReplyQueue());
        template.setReplyTimeout(1000);
        return template;
    }

    @Bean
    public UserRabbitGateway userRabbitGateway(){
        UserRabbitGateway userRabbitGateway = new UserRabbitGateway();
        userRabbitGateway.setRabbitTemplate(rabbitTemplate());
        return userRabbitGateway;
    }

    @Bean
    public Queue userReplyQueue(){ return new Queue(USER_REPLY_QUEUE_NAME);}
}
