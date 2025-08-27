package vn.couple_app.file_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.couple_app.file_service.validator.image.ImageFileConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteImageRequest {
    @NotBlank(message = "FIELD_REQUIRED")
    String id;
    @NotBlank(message = "FIELD_REQUIRED")
    String userId;
}
