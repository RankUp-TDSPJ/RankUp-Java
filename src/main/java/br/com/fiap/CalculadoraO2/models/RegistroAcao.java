package br.com.fiap.CalculadoraO2.models;

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

    public int getId_acao() {
        return id_acao;
    }

    public void setId_acao(int id_acao) {
        this.id_acao = id_acao;
    }

    public AcaoSustentavel getAcao() {
        return acao;
    }

    public void setAcao(AcaoSustentavel acao) {
        this.acao = acao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
