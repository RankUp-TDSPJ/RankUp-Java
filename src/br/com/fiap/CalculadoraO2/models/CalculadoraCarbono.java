package br.com.fiap.CalculadoraO2.models;

public class CalculadoraCarbono {

    private static double fatorEmissao;

    public static double calcularImpacto(AcaoSustentavel acao) {
        return acao.getFatorCO2() * fatorEmissao;
    }

    public static int avaliarAcao (AcaoSustentavel acao) {
        double impacto = calcularImpacto(acao);
        int pontuacao = (int)impacto *10;

        if (pontuacao > 100) {
            pontuacao = 100;
        }
        if (pontuacao < 0) {
            pontuacao = 0;
        }
        return pontuacao;
    }

    public double getFatorEmissao() {
        return fatorEmissao;
    }

    public void setFatorEmissao(double fatorEmissao) {
        this.fatorEmissao = fatorEmissao;
    }
}
