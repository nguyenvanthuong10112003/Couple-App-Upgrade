package vn.couple_app.api.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    final ErrorCode errorCode;
    public AppException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
