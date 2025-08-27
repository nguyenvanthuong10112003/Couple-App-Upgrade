package vn.couple_app.file_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.file_service.validator.image.ImageFileConstraint;

@Data
public class SaveImageRequest {
    @NotNull(message = "FIELD_REQUIRED")
    @ImageFileConstraint(message = "IMAGE_FILE_INVALID")
    MultipartFile file;
}