package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.enums.Classificacao;
import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class AcaoSustentavel extends Acao implements Exibivel {

    private double fatorCO2;
    private double pontosPorAcao;
    private String unidade;

    public AcaoSustentavel(String nome, String descricao, double fatorCO2, double pontosPorAcao, String unidade) {
        super(nome, descricao, Classificacao.SUSTENTAVEL);
        this.unidade = unidade;
        this.fatorCO2 = fatorCO2;
        this.pontosPorAcao = pontosPorAcao;
    }

    @Override
    public void exibirAcao() {
        System.out.println("Acao: " + getNome() +
                " | Descricao: " + getDescricao() +
                " | Fator CO2: " + fatorCO2 +
                " | Pontos: " + pontosPorAcao +
                " | Unidade: " + unidade);
    }

    @Override
    public void exibir() {
        exibirAcao();

    }



    public double getFatorCO2() {
        return fatorCO2;
    }
    public double getPontosPorAcao() {
        return pontosPorAcao;
    }
    public String getUnidade() {
        return unidade;
    }
}
