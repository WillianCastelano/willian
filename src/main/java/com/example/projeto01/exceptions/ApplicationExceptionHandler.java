package com.example.projeto01.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@Slf4j//annotation do Lombok para gerar automaticamente um logger para a classe onde ela é usada.
@ControllerAdvice
  public class ApplicationExceptionHandler extends  ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        log.info("Caiu no RunTimeException");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + ex.getMessage());

    }
}