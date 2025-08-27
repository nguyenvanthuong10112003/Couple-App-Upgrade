package vn.couple_app.file_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${services.rabbit.exchange}")
    String exchangeStr;
    @Value("${services.rabbit.file-service.del-image.routing}")
    String routingDelImage;
    @Value("${services.rabbit.file-service.del-image.queue}")
    String queueDelImage;

    @Bean
    public Queue queue()
    {
        return new Queue(queueDelImage);
    }

    @Bean
    public Exchange exchange()
    {
        return new TopicExchange(exchangeStr);
    }

    @Bean
    public
    Binding binding(Queue queue, Exchange exchange)
    {
        return BindingBuilder.bind(queue)
            .to(exchange)
            .with(routingDelImage)
            .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
