package vn.couple_app.file_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized exception", HttpStatus.BAD_REQUEST),


    // DATA
    VALIDATOR_ERROR(2000, "Have some validator errors", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_FOUND(2001, "Image not found", HttpStatus.BAD_REQUEST),
    IMAGE_FILE_INVALID(2002, "Image file is invalid", HttpStatus.BAD_REQUEST),

    // AUTH
    UNAUTHENTICATED(9000, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(9001, "UNAUTHORIZED", HttpStatus.UNAUTHORIZED)

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
