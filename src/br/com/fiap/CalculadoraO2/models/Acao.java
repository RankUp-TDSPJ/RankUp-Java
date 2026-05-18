package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.enums.Classificacao;

public abstract class Acao {

    private String nome;
    private String descricao;
    private Classificacao classificacao;

    public Acao (String nome, String descricao, Classificacao classificacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.classificacao = classificacao;
    }

    public abstract void exibirAcao();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
