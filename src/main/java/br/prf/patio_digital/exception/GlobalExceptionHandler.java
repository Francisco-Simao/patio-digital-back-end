package br.prf.patio_digital.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<?> handleCpfInvalido(CpfInvalidoException ex) {

        return ResponseEntity.status(403)
                .body(new ErrorResponse(ex.getMessage(), 403));
    }
}