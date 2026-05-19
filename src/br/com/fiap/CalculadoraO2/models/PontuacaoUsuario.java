package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class PontuacaoUsuario implements Exibivel {

    private double pontuacaoTotal;
    private AcaoSustentavel ultimaAcao;

    public double calculcarPontuacao() {
        return  1;
    }

    @Override
    public void exibir() {

    }

    public double getPontuacaoTotal() {
        return pontuacaoTotal;
    }
}
