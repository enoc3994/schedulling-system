package com.schedullingsystem.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> exceptionStudent(StudentNotFoundException exception) {
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ClassesNotFoundException.class)
    public ResponseEntity<Object> exceptionClass(ClassesNotFoundException exception) {
        return new ResponseEntity<>("Class not found", HttpStatus.NOT_FOUND);
    }

}
