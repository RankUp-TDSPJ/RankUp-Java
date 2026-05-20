package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class Usuario implements Exibivel {

    private String nome;
    private String email;
    private int idade;
    private PontuacaoUsuario pontuacao;

    public Usuario (String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.pontuacao = null;

    }

    public void exibirPerfil() {
        System.out.println("==== Perfil do Usuario ====");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade);
        if (pontuacao != null) {
            pontuacao.exibirPontuacao();
        } else {
            System.out.println("Nenhuma acao registrada ainda.");
        }

    }

    @Override
    public void exibir() {
        exibirPerfil();
    }



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public PontuacaoUsuario getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(PontuacaoUsuario pontuacao) {
        this.pontuacao = pontuacao;
    }
}
