package port.sm.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        e.printStackTrace(); // ✅ 무조건 콘솔에 원인 찍힘
        return ResponseEntity.status(500).body("ERROR: " + e.getClass().getName() + " / " + e.getMessage());
    }
}