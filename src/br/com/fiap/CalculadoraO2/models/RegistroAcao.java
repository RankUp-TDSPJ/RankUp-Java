package br.com.fiap.CalculadoraO2.models;

public class RegistroAcao {

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



    public AcaoSustentavel getAcao() {
        return acao;
    }
    public double getQuantidade() {
        return quantidade;
    }
    public String getData() {
        return data;
    }
}
