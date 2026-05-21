package br.com.fiap.CalculadoraO2.models;

public class CalculadoraCarbono {

    private static double fatorEmissao = 0.5;

    public static double calcularImpacto(AcaoSustentavel acao, double quantidade) {
        return acao.getFatorCO2() * quantidade * fatorEmissao;
    }

    public static int avaliarAcao(AcaoSustentavel acao, double quantidade) {
        double impacto = calcularImpacto(acao, quantidade);
        int pontuacao = (int) (impacto * 10);

        if (pontuacao > 100) pontuacao = 100;
        if (pontuacao < 0) pontuacao = 0;

        return pontuacao;
    }


    public double getFatorEmissao() {
        return fatorEmissao;
    }
    public void setFatorEmissao(double fatorEmissao) {
        CalculadoraCarbono.fatorEmissao = fatorEmissao;
    }
}