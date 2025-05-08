package com.example.duplicatedetector.exception;

import com.example.duplicatedetector.dto.ProblemDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation error: {}", ex.getMessage());
        ProblemDetails problem = new ProblemDetails(
            "https://example.com/validation-error",
            "Validation Error",
            HttpStatus.BAD_REQUEST.value(),
            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
            "/api/transactions/check"
        );
        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleAllUncaughtException(Exception ex) {
        logger.error("Unexpected error occurred: ", ex);
        ProblemDetails problem = new ProblemDetails(
            "https://example.com/internal-error",
            "Internal Server Error",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred",
            "/api/transactions/check"
        );
        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 