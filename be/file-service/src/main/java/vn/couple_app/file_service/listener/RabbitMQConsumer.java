package vn.couple_app.file_service.listener;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.couple_app.file_service.dto.request.DeleteImageRequest;
import vn.couple_app.file_service.exception.AppException;
import vn.couple_app.file_service.service.ImageService;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

    final ImageService imageService;
    final Validator validator;

    @RabbitListener(queues = "${services.rabbit.file-service.del-image.queue}")
    public void receiveMessage(DeleteImageRequest request)
    {
        log.info("received: {}", request);
        Set<ConstraintViolation<DeleteImageRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            log.info("RabbitMQConsumer error: dto request invalid");
            return;
        }
        try {
            imageService.delete(request);
        } catch (AppException appException) {
            log.error("App exception: {}", appException.getMessage());
        }
    }

}