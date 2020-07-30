package com.zirubihara.phototraveller.phototraveller.Advice;

import com.zirubihara.phototraveller.phototraveller.exceptions.ValidationErrorsDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class UserAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandling(MethodArgumentNotValidException e){
        ValidationErrorsDetails validationErrorsDetails = new ValidationErrorsDetails(Instant.now(),"Błąd formularza", e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(validationErrorsDetails, HttpStatus.BAD_REQUEST);
    }
}
