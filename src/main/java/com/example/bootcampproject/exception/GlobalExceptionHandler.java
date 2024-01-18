package com.example.bootcampproject.exception;

import com.example.bootcampproject.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> UserNotFoundException(UserNotFoundException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorMessage> InvalidQuantity(InvalidQuantityException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(message);
    }

    @ExceptionHandler(InvalidSellException.class)
    public ResponseEntity<ErrorMessage> InvalidSellException(InvalidSellException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN,exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(message);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ErrorMessage> StockNotFoundException(StockNotFoundException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(InvalidTradeTypeException.class)
    public ResponseEntity<ErrorMessage> InvalidTradeTypeException(InvalidTradeTypeException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN,exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(message);
    }


}
