package br.com.fiap.CalculadoraO2.models;

public class PontuacaoUsuario extends Usuario{

    private double pontuacaoTotal;
    private AcaoSustentavel ultimaAcao;

    public double calculcarPontuacao() {
        return  1;
    }

    @Override
    public void exibirPerfil() {
        super.exibirPerfil();
    }

    public double getPontuacaoTotal() {
        return pontuacaoTotal;
    }
}
