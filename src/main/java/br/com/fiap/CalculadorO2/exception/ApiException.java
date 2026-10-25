package br.com.fiap.CalculadorO2.exception;

public class ApiException extends RuntimeException{

    private final int status;

    public ApiException(int status, String mensagem) {
        super(mensagem);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
