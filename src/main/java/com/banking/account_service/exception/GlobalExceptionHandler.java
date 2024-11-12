package com.banking.account_service.exception;


import com.banking.account_service.response.InvalidResponse;
import com.banking.account_service.response.NotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistedException.class)
    public ResponseEntity<InvalidResponse> handleUserAlreadyExisted(UserAlreadyExistedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new InvalidResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundResponse> handleUserNotFound(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidateException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<NotFoundResponse> handleInvalidRequest(InvalidRequestException exception){
        return ResponseEntity.status(400).body(new NotFoundResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now()));
    }
}
