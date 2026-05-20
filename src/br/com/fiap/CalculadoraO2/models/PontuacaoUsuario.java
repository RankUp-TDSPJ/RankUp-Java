package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class PontuacaoUsuario implements Exibivel {

    private double pontuacaoTotal;
    private AcaoSustentavel ultimaAcao;

    public PontuacaoUsuario(AcaoSustentavel ultimaAcao) {
        this.ultimaAcao = ultimaAcao;
        this.pontuacaoTotal = 0;
    }

    public double calculcarPontuacao() {
        int pontos = CalculadoraCarbono.avaliarAcao(ultimaAcao);
        this.pontuacaoTotal += pontos;
        return pontuacaoTotal;
    }

    public void exibirPontuacao() {
        System.out.println("=== Pontuacao do Usuario ===");
        System.out.println("Ultima acao: " + ultimaAcao.getNome());
        System.out.println("Pontuacao total: " + pontuacaoTotal);
        System.out.println("Impacto CO2: " + CalculadoraCarbono.calcularImpacto(ultimaAcao) + " kg");
    }

    @Override
    public void exibir() {
        exibirPontuacao();
    }



    public double getPontuacaoTotal() {
        return pontuacaoTotal;
    }
    public void setPontuacaoTotal(double pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public AcaoSustentavel getUltimaAcao() {
        return ultimaAcao;
    }
    public void setUltimaAcao(AcaoSustentavel ultimaAcao) {
        this.ultimaAcao = ultimaAcao;
    }
}
