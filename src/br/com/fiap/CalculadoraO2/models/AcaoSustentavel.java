package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.enums.Classificacao;
import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class AcaoSustentavel extends Acao implements Exibivel {

    private double fatorCO2;
    private double pontosPorAcao;

    public AcaoSustentavel(String nome, String descricao, double fatorCO2, double pontosPorAcao) {
        super(nome, descricao, Classificacao.SUSTENTAVEL);
        this.fatorCO2 = fatorCO2;
        this.pontosPorAcao = pontosPorAcao;
    }

    @Override
    public void exibirAcao() {
        System.out.println("Acao: " + getNome());
        System.out.println("Descricao: " + getDescricao());
        System.out.println("Classificacao: " + getClassificacao());
        System.out.println("Fator CO2: " + fatorCO2 + " kg");
        System.out.println("Pontos por acao: " + pontosPorAcao);

    }

    @Override
    public void exibir() {
        exibirAcao();
    }



    public double getFatorCO2() {
        return fatorCO2;
    }
    public void setFatorCO2(double fatorCO2) {
        this.fatorCO2 = fatorCO2;
    }

    public double getPontosPorAcao() {
        return pontosPorAcao;
    }
    public void setPontosPorAcao(double pontosPorAcao) {
        this.pontosPorAcao = pontosPorAcao;
    }


}
