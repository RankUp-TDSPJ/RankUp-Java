package br.com.fiap.CalculadorO2.dto;

public record UsuarioResumoResponse(int id, String nome, String email, int idade, String tipo, double pontuacaoTotal) {
}
