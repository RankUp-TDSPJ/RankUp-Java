package br.com.fiap.CalculadorO2.dto;

public record RegistroCriadoResponse(int idRegistro, String nomeAcao, double quantidade, String data, double pontosGanhos, double co2Evitado, double pontuacaoTotal, String nivel) {
}
