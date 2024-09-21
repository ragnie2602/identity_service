package com.ragnie.identity_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ragnie.identity_service.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity.badRequest().body(new ApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(RuntimeException exception) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(ErrorCode.UNCATEGORIED_ERROR.getCode(),
                        ErrorCode.UNCATEGORIED_ERROR.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        /*
         * Because cannot use ErrorCode.INVALID_USERNAME.getMessage(),
         * we have to find object INVALID_USERNAME by algorithm through
         * the type name of object: "INVALID_USERNAME"
         */

        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (Exception e) {
        }

        return ResponseEntity.badRequest().body(new ApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
    }
}
