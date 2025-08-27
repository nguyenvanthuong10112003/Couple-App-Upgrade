package vn.couple_app.file_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import vn.couple_app.file_service.exception.ErrorCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class ResponseApi<T> {
    int code;
    String message;
    T data;

    public static <T> ResponseApi<T> createSuccess(T t) {
        return ResponseApi
            .<T>builder()
            .code(200)
            .message("success")
            .data(t)
            .build();
    }

    public static ResponseApi<?> createSuccess() {
        return ResponseApi
            .builder()
            .code(200)
            .message("success")
            .build();
    }

    public static ResponseApi<?> createError(ErrorCode errorCode) {
        return ResponseApi
            .builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
    }
}