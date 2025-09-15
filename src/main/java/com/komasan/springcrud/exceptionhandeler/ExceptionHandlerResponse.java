package com.komasan.springcrud.exceptionhandeler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerResponse {

    private final String errorName = "You need to write at least 3 letters";
    private final String errorAge = "You need to enter your age";
    private final String errorEmail = "You need to enter your email with @";

    @ExceptionHandler
    public ResponseEntity<String> responseException(MethodArgumentNotValidException e) {

        Set<String> errors = new HashSet<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            // every error is an ObjectError
            // but if error relates to particular field example (username)
            FieldError fieldError = (FieldError) error;
            // just getting Field where we found ann error
            String fieldName = fieldError.getField();

            switch (fieldName) {
                case "username" -> errors.add(errorName);
                case "age" -> errors.add(errorAge);
                case "email" -> errors.add(errorEmail);
            }
        });
        log.warn("Validation failed: {}", errors);

        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);

    }
}
