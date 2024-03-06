package com.ua.hlibkorobov.testreport.controller;

import com.ua.hlibkorobov.testreport.exception.SalesAndTrafficByAsinNotFoundException;
import com.ua.hlibkorobov.testreport.exception.StatisticByAsinNotFoundException;
import com.ua.hlibkorobov.testreport.exception.StatisticByDateNotFoundException;
import com.ua.hlibkorobov.testreport.exception.UserNotFoundException;
import com.ua.hlibkorobov.testreport.exception.IncorrectTimeRangeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return ResponseEntity.badRequest().body(e.getBindingResult().toString());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        log.warn(e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleStatisticByDateNotFoundException(StatisticByDateNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleStatisticByAsinNotFoundException(StatisticByAsinNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<?> handleIncorrectTimeRangeException(IncorrectTimeRangeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<?> handleSalesAndTrafficByAsinNotFoundException(SalesAndTrafficByAsinNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
