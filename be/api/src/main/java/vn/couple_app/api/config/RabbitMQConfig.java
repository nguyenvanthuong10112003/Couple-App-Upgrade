package vn.couple_app.api.config;

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
    String routingDelImageFileService;
    @Value("${services.rabbit.file-service.del-image.queue}")
    String queueDelImageFileService;
    @Value("${services.rabbit.notification-service.send-email.routing}")
    String routingSendEmailNotificationService;
    @Value("${services.rabbit.notification-service.send-email.queue}")
    String queueSendEmailNotificationService;

    @Bean
    public Queue delImageFileServiceQueue()
    {
        return new Queue(queueDelImageFileService);
    }

    @Bean
    public Queue sendEmailNotificationServiceQueue()
    {
        return new Queue(queueSendEmailNotificationService);
    }

    @Bean
    public Exchange exchange()
    {
        return new TopicExchange(exchangeStr);
    }

    @Bean
    public
    Binding bindingDelImageFileService(Queue delImageFileServiceQueue, Exchange exchange)
    {
        return BindingBuilder.bind(delImageFileServiceQueue)
            .to(exchange)
            .with(routingDelImageFileService)
            .noargs();
    }

    @Bean
    public
    Binding bindingSendEmailNotificationService(Queue sendEmailNotificationServiceQueue, Exchange exchange)
    {
        return BindingBuilder.bind(sendEmailNotificationServiceQueue)
            .to(exchange)
            .with(routingSendEmailNotificationService)
            .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}