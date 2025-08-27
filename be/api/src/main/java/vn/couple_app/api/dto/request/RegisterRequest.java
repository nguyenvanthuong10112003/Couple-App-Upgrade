package vn.couple_app.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import vn.couple_app.api.validator.email.EmailConstraint;
import vn.couple_app.api.validator.password.PasswordConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @EmailConstraint(message = "EMAIL_INVALID")
    @NotBlank(message = "EMAIL_IS_REQUIRED")
    String email;
    @PasswordConstraint(message = "PASSWORD_INVALID", min = 6)
    @NotBlank(message = "PASSWORD_IS_REQUIRED")
    String password;
}