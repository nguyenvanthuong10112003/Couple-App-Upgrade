package vn.couple_app.api.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendEmailDto {

    enum Template {
        WELCOME("/")
        ;
        Template(String v) {
            this.value = v;
        }
        final String value;
    }

    String receiver;
    String title;
    String template;
    Map<String, String> keyValue;
}
