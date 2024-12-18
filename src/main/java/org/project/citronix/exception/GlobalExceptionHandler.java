package org.project.citronix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SuperficieNonCompatibleException.class)
    public ResponseEntity<ExceptionDetails> handleEntityNotFoundException(SuperficieNonCompatibleException ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ValueNotExpectedException.class)
    public ResponseEntity<ExceptionDetails> handleValueNotExpectedException(ValueNotExpectedException ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PlusDeDixChampsException.class)
    public ResponseEntity<ExceptionDetails> handlePlusDeDixChampsException(PlusDeDixChampsException ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ArbreDateNotCompatible.class)
    public ResponseEntity<ExceptionDetails> handleArbreDateNotCompatible(ArbreDateNotCompatible ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_ACCEPTABLE);
    }
}
