package br.com.fiap.CalculadoraO2.models;

public class AcaoSustentavel extends Acao{

    private double fatorCO2;
    private double pontosPorAcao;

    @Override
    public void exibirAcao() {
        super.exibirAcao();
    }

    public double getFatorCO2() {
        return fatorCO2;
    }

    public double getPontosPorAcao() {
        return pontosPorAcao;
    }

}
