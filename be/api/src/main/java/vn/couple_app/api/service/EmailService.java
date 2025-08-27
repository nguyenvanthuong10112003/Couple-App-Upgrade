package vn.couple_app.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.couple_app.api.definition.SendEmailDefine;
import vn.couple_app.api.dto.request.SendEmailDto;
import vn.couple_app.api.entity.User;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    final RabbitMQProducer rabbitMQProducer;
    public void welcomeUser(User user) {
        String fullName = user.getFullName();
        String email = user.getAccount().getEmail();
        Map<String, String> mapping = new HashMap<>()
            {{put(SendEmailDefine.Attribute.WELCOME_USER.NAME.getValue(), fullName);}};
        rabbitMQProducer.sendEmail(SendEmailDto.builder()
            .receiver(email)
            .title(SendEmailDefine.Title.WELCOME_USER.getValue())
            .template(SendEmailDefine.Template.WELCOME_USER.getValue())
            .keyValue(mapping)
            .build()
        );
    }
}
