package br.com.fiap.CalculadorO2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TratadordeErros {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> tratar(ApiException e) {
        return ResponseEntity.status(e.getStatus()).body(Map.of("erro", e.getMessage()));
    }
}
