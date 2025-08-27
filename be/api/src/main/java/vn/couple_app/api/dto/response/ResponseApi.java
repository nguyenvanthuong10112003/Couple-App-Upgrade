package vn.couple_app.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.couple_app.api.exception.ErrorCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
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