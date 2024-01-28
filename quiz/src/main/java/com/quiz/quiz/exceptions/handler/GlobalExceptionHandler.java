package com.quiz.quiz.exceptions.handler;

import com.quiz.quiz.exceptions.ObjectNotFoundException;
import com.quiz.quiz.globals.ResponseDto;
import com.quiz.quiz.utils.ResponseBuilder;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseDto<Object> ObjectNotFoundError(ObjectNotFoundException ex){
        return ResponseBuilder.badRequest(ex.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public final ResponseDto<Object> IoError(IOException ex){
        return ResponseBuilder.badRequest("IO Error");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDto<Object> myConstraintsViolationException(ConstraintViolationException e) {
        Map<String, String> res = new HashMap<>();
        e.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            res.put(fieldName, message);
        });
        return ResponseBuilder.badRequest(res);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseDto<Object> duplicateEntryError(DataIntegrityViolationException ex){
        return ResponseBuilder.badRequest("Duplicate Entry");
    }


    @ExceptionHandler(RuntimeException.class)
    public final ResponseDto<Object> handleRuntimeExceptions(RuntimeException ex) {
        log.info(ex.getMessage());
        return ResponseBuilder.badRequest("Something went wrong with the server");
    }

    @ExceptionHandler(Exception.class)
    public final ResponseDto<Object> handleGeneralExceptions(Exception ex) {
        return ResponseBuilder.badRequest("Something went wrong");
    }

}
