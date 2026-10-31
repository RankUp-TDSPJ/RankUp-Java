package br.com.fiap.CalculadorO2.models;

import br.com.fiap.CalculadorO2.interfaces.Exibivel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Usuario implements Exibivel {

    private int id;
    private String nome;
    private String email;
    private int idade;
    private PontuacaoUsuario pontuacao;

    public abstract String getTipo();
    public abstract void exibirPermissoes();


    public Usuario (String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.pontuacao = new PontuacaoUsuario();

    }

    public void exibirPerfil() {
        System.out.println("\n=== Perfil do Usuario ===");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade);
        pontuacao.exibirPontuacao();
    }

    @Override
    public void exibir() {
        exibirPerfil();
    }


}
