package br.com.fiap.CalculadorO2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

// Todos os erros da API saem no mesmo formato: { "erro": "mensagem" }
@RestControllerAdvice
public class TratadordeErros {

    private static final Logger log = LoggerFactory.getLogger(TratadordeErros.class);

    // erros de regra de negócio lançados pelos services (400, 403, 404, 409...)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> tratar(ApiException e) {
        return ResponseEntity.status(e.getStatus()).body(Map.of("erro", e.getMessage()));
    }

    // corpo JSON malformado ou id/header com tipo errado (ex.: /usuarios/abc)
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, String>> tratarRequisicaoInvalida(Exception e) {
        return ResponseEntity.status(400)
                .body(Map.of("erro", "Requisição inválida: verifique o corpo JSON e os parâmetros enviados."));
    }

    // erros do próprio Spring (rota inexistente, verbo não suportado, header faltando...)
    // mantêm o status original; qualquer outra exceção vira 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> tratarGenerico(Exception e) {
        if (e instanceof ErrorResponse resposta) {
            int status = resposta.getStatusCode().value();
            String mensagem = status == 404 ? "Recurso não encontrado." : "Requisição inválida.";
            return ResponseEntity.status(status).body(Map.of("erro", mensagem));
        }
        log.error("Erro inesperado na API", e);
        return ResponseEntity.status(500).body(Map.of("erro", "Erro interno no servidor."));
    }
}
