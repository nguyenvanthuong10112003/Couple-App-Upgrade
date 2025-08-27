package vn.couple_app.api.validator.image;

import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageFileValidator.class)
public @interface ImageFileConstraint {
    String message() default "Image file invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
