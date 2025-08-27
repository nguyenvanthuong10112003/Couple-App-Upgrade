package vn.couple_app.file_service.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    final ErrorCode errorCode;
    public AppException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
