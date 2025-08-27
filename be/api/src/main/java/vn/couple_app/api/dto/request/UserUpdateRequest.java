package vn.couple_app.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.api.validator.image.ImageFileConstraint;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotBlank(message = "FULL_NAME_CANNOT_BE_EMPTY")
    String fullName;
    String alias;
    @NotNull(message = "DOB_REQUIRED")
    LocalDate dob;
    @NotNull(message = "GENDER_REQUIRED")
    Boolean gender;
    String lifeStory;
    @ImageFileConstraint(message = "IMAGE_FILE_INVALID")
    MultipartFile avatarFile;
}
