package vn.couple_app.file_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IntrospectRequest {
    @NotBlank(message = "FIELD_REQUIRED")
    String token;
}
