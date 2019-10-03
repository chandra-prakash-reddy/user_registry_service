package com.employeeregistry.handlers;


import com.employeeregistry.conrollers.EmployeeRegistryController;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.Logger;

@ControllerAdvice(basePackageClasses = EmployeeRegistryController.class)
@RestController
public class RegistryExceptionHandler  {
    private static final Logger logger = Logger.getLogger(RegistryExceptionHandler.class.getName());

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> handleUnAuthorizedException(UnAuthorizedException ex, WebRequest request){
        logger.info("exception handled at 401");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler({EmployeeExistsException.class, UserExistsException.class,Exception.class})
    public ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request){
        logger.info("exception handled at 500");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleNoContentError(EmployeeNotFoundException ex, WebRequest request){
        logger.info("exception handled at 204");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }
}
