package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class Usuario implements Exibivel {

    private String nome;
    private String email;
    private int idade;
    private PontuacaoUsuario pontuacao;

    public void exibirPerfil() {

    }

    @Override
    public void exibir() {

    }

    public String getNome() {
        return nome;
    }
}
