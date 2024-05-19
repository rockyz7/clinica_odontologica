package com.example.demo.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException extends Exception {

    private static final Logger logger = Logger.getLogger(GlobalException.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarResourceNotFoundException(ResourceNotFoundException ex){
        logger.error("Error, el sistema detectó un problema, se registró el siguiente mensaje: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarBadRequestException(BadRequestException bre){
        logger.error("Error, el sistema detectó un problema, se registró el siguiente mensaje: " + bre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }
}
