package com.aman.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice("com.fincity.assignment")
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiErrorResponse> entityAlreadyExistsException(BaseException e) {
        LOGGER.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        e.getHttpStatus().value(),
                        e.getApiResponseMessage()
                ),
                e.getHttpStatus()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> runtimeException(RuntimeException e) {
        LOGGER.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Something went wrong"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiValidationErrorResponse> validationExceptionHandler(MethodArgumentNotValidException e) {

        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                fieldError -> validationErrors.put(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                )
        );
        e.getBindingResult().getGlobalErrors().forEach(
                globalError -> validationErrors.put(
                        globalError.getObjectName(),
                        globalError.getDefaultMessage()
                )
        );
        ApiValidationErrorResponse validationErrorResponse = new ApiValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),validationErrors
        );
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
