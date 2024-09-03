package com.empleado.exceptions.controllerExceptions;

import com.empleado.exceptions.EmployeeNotFound;
import com.empleado.exceptions.EmployeeRegistered;
import com.empleado.exceptions.DuplicateAttribute;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionEmployeeRegistered {

    @ExceptionHandler(EmployeeRegistered.class)
    public ResponseEntity<String> handleEmployeeRegisteredException(EmployeeRegistered ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateAttribute.class)
    public ResponseEntity<String> handleDuplicateAttribute(DuplicateAttribute ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
