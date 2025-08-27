package vn.couple_app.file_service.exception;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.couple_app.file_service.dto.response.ResponseApi;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseApi<?>> runtimeExceptionHandler(RuntimeException runtimeException) {
        log.error("Error: {}", runtimeException.getMessage());
        return ResponseEntity
            .badRequest()
            .body(ResponseApi.createError(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseApi<?>> appExceptionHandler(AppException appException) {
        log.error("Error: {}", appException.getMessage());
        ErrorCode errorCode = appException.getErrorCode();
        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(ResponseApi.createError(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseApi<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        var errors = exception.getAllErrors();
        log.error("Error: {}", errors
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining()));
        ErrorCode errorCode = ErrorCode.VALIDATOR_ERROR;
        Map<String, Object> attributes = null;
        try {
            errorCode = ErrorCode.valueOf(errors.get(0).getDefaultMessage());
            attributes = exception
                    .getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .unwrap(ConstraintViolation.class)
                    .getConstraintDescriptor()
                    .getAttributes();
            log.info("Attributes: {}", attributes.toString());
        } catch (Exception e) {}
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ResponseApi
                        .builder()
                        .code(errorCode.getCode())
                        .message(mapAttribute(attributes, errorCode.getMessage()))
                        .build());
    }

    private String mapAttribute(Map<String, Object> attributes, String rsl) {
        if (CollectionUtils.isEmpty(attributes)) return rsl;
        if (Strings.isEmpty(rsl)) return rsl;
        for (String key : attributes.keySet()) {
            rsl = rsl.replace("{" + key + "}",
                    Objects.requireNonNullElse(attributes.get(key), "").toString());
        }
        return rsl;
    }
}
