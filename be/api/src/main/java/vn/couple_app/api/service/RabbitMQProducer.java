package vn.couple_app.api.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.couple_app.api.dto.request.DeleteImageRequest;
import vn.couple_app.api.dto.request.SendEmailDto;

@Component
public class RabbitMQProducer {
    @Value("${services.rabbit.exchange}")
    String exchangeStr;
    @Value("${services.rabbit.file-service.del-image.routing}")
    String routingDelImageFileService;
    @Value("${services.rabbit.notification-service.send-email.routing}")
    String routingSendEmailNotificationService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void delImage(DeleteImageRequest request)
    {
        rabbitTemplate.convertAndSend(
            exchangeStr, routingDelImageFileService, request);
    }

    public void sendEmail(SendEmailDto sendEmailDto)
    {
        rabbitTemplate.convertAndSend(
            exchangeStr, routingSendEmailNotificationService, sendEmailDto
        );
    }
}