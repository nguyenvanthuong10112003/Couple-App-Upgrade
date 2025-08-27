package vn.couple_app.api.validator.image;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageFileValidator implements ConstraintValidator<ImageFileConstraint, MultipartFile> {

    @Override
    public void initialize(ImageFileConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            return true;
        }
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
