package com.realestateproject.exception;

import com.realestateproject.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ClientNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
//        ErrorResponse errorDetails = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false), new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(SaleNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleSaleNotFoundException(SaleNotFoundException ex, WebRequest request) {
//        ErrorResponse errorDetails = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false), new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
//    // More handlers here for different exception types...
//
//    @ExceptionHandler(AgentNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleAgentNotFoundException(AgentNotFoundException ex, WebRequest request) {
//        ErrorResponse errorDetails = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                ex.getMessage(),
//                request.getDescription(false),
//                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())
//        );
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false), new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(GenericNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                request.getDescription(false),
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())
        );
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }
}