package com.example.twitter_mock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomApiException> handleResourceNotFoundException(ResourceNotFoundException ex) {
        CustomApiException errorDetails = CustomApiException.builder()
                .message(ex.getMessage())
                .details("Kaynak bulunamadı!")
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomApiException> handleIllegalStateException(IllegalStateException ex) {
        CustomApiException errorDetails = CustomApiException.builder()
                .message(ex.getMessage())
                .details("Geçersiz işlem yürütüldü: İş kuralı hatası.")
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiException> handleGlobalException(Exception ex) {
        CustomApiException errorDetails = CustomApiException.builder()
                .message("Bilinmeyen bir hata oluştu")
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
