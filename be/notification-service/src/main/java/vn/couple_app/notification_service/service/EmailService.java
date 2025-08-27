package vn.couple_app.notification_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import vn.couple_app.notification_service.dto.SendEmailDto;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    @Value("${spring.mail.from}")
    String mailFrom;
    @Value("${spring.mail.username}")
    String email;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendEmail(SendEmailDto dto) {
        try {
            createMail(dto);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Error: {}", e.getMessage());
        }
    }

    private void createMail(SendEmailDto dto) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
        if (!Objects.requireNonNullElse(dto.getMapping(), Collections.emptyMap())
            .isEmpty())
            dto.getMapping().forEach(context::setVariable);

        String body = templateEngine.process(dto.getTemplate(), context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(dto.getReceiver());
        helper.setSubject(dto.getTitle());
        helper.setText(body, true);
        helper.setFrom(email, mailFrom);

        mailSender.send(message);
    }
}
