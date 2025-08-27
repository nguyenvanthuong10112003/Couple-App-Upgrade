package vn.couple_app.notification_service.listener;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import vn.couple_app.notification_service.dto.SendEmailDto;
import vn.couple_app.notification_service.service.EmailService;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
    final RabbitTemplate template;
    final EmailService emailService;
    final Validator validator;
    @RabbitListener(queues = "${services.rabbit.notification-service.send-email.queue}")
    void receivedMessage(SendEmailDto dto) {
        log.info("Message received: {}", dto);
        Set<ConstraintViolation<SendEmailDto>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            log.info("RabbitMQConsumer error: dto request invalid");
            return;
        }
        emailService.sendEmail(dto);
    }
}
