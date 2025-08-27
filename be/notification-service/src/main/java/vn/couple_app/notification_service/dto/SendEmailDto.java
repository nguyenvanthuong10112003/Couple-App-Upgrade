package vn.couple_app.notification_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import vn.couple_app.notification_service.validator.email.EmailConstraint;

import java.util.Map;

@Data
public class SendEmailDto {
    @NotBlank
    @EmailConstraint
    String receiver;
    @NotBlank
    String title;
    @NotBlank
    String template;
    Map<String, String> mapping;
}
