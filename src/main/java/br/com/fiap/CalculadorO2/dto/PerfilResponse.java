package br.com.fiap.CalculadorO2.dto;

public record PerfilResponse(int id, String nome, String email, int idade, String tipo, double pontuacaoTotal, String nivel, double co2TotalEvitado) {
}
