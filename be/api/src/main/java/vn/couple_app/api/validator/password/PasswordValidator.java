package vn.couple_app.api.validator.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    int min;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isEmpty(s)) return false;
        if (s.length() < min) return false;
        return true;
    }
}
