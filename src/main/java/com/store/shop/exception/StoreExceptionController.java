package com.store.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class StoreExceptionController {

    @ExceptionHandler(value = CartIsEmptyException.class)
    public ResponseEntity<Object> exception(CartIsEmptyException exception) {
        return new ResponseEntity<>("Cart is empty",
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception) {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OrderItemNotFoundException.class)
    public ResponseEntity<Object> exception(OrderItemNotFoundException exception) {
        return new ResponseEntity<>("Order item not found",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OrderItemNotAccessibleException.class)
    public ResponseEntity<Object> exception(OrderItemNotAccessibleException exception) {
        return new ResponseEntity<>("Order item not accessible",
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exception(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
