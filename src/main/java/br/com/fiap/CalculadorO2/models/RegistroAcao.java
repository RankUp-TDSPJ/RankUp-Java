package br.com.fiap.CalculadorO2.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroAcao {

    private int id_acao;
    private AcaoSustentavel acao;
    private double quantidade;
    private String data;

    public RegistroAcao(AcaoSustentavel acao, double quantidade, String data) {
        this.acao = acao;
        this.quantidade = quantidade;
        this.data = data;
    }

    public double calcularImpactoCO2() {
        return acao.getFatorCO2() * quantidade;
    }

    public double calcularPontos() {
        return acao.getPontosPorAcao() * quantidade;
    }

    public void exibirRegistro() {
        System.out.println("- " + acao.getNome() +
                " | Quantidade: " + quantidade +
                " | Pontos: " + calcularPontos() +
                " | CO2: " + calcularImpactoCO2() + " kg" +
                " | Data: " + data);
    }

}
