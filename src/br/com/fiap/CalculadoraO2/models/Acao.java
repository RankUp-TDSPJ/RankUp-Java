package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.enums.Classificacao;

public abstract class Acao {

    private String nome;
    private String descricao;
    private Classificacao classificacao;

    public void exibirAcao() {
    }

    public String getNome() {
        return nome;
    }
}
