package br.com.fiap.CalculadorO2.dto;

public record HistoricoResponse(int idRegistro, String nomeAcao, String unidade, double quantidade, String data, double pontos, double co2Evitado) {
}
