package vn.couple_app.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized exception", HttpStatus.BAD_REQUEST),

    // VALIDATOR ERRORS
    VALIDATOR_ERROR(2000, "Have some validator errors", HttpStatus.BAD_REQUEST),
    EMAIL_IS_REQUIRED(2001, "Email is required", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_REQUIRED(2002, "Password is required", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(2003, "Email is invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2004, "Password must be at least {min} character", HttpStatus.BAD_REQUEST),
    FIELD_REQUIRED(2005, "This field is required", HttpStatus.BAD_REQUEST),
    FULL_NAME_CANNOT_BE_EMPTY(2006, "Full name cannot be empty", HttpStatus.BAD_REQUEST),
    DOB_REQUIRED(2007, "Dob is required", HttpStatus.BAD_REQUEST),
    GENDER_REQUIRED(2008, "Gender is required", HttpStatus.BAD_REQUEST),
    IMAGE_FILE_INVALID(2009, "Image file is invalid", HttpStatus.BAD_REQUEST),

    // Data
    ACCOUNT_EXISTED(3001, "Account existed", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(3002, "Account not found", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(3003, "Password is incorrect", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(3004, "User not found", HttpStatus.BAD_REQUEST),

    // AUTH
    UNAUTHENTICATED(9000, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED)
    ;
    final int code;
    final String message;
    final HttpStatus httpStatus;
    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}