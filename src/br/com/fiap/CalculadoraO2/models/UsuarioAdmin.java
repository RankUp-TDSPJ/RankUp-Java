package br.com.fiap.CalculadoraO2.models;

import java.util.List;

public class UsuarioAdmin extends Usuario{

    public UsuarioAdmin(String nome, String email, int idade) {
        super(nome, email, idade);
    }

    @Override
    public String getTipo() {
        return "ADMIN";
    }

    @Override
    public void exibirPermissoes() {
        System.out.println("Permissoes: tudo do usuario comum, alem de gerenciar outros usuarios.");
    }

    public void visualizarTodosUsuarios(List<Usuario> usuarios) {
        System.out.println("\n=== Todos os usuarios (visao admin) ===");
        for (Usuario u : usuarios) {
            System.out.println(u.getNome() + " - " + u.getTipo());
        }
    }
}
