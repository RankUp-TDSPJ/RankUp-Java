package br.com.fiap.CalculadoraO2.models;

public class UsuarioComum extends Usuario{


    public UsuarioComum(String nome, String email, int idade) {
        super(nome, email, idade);
    }

    @Override
    public String getTipo() {
        return "COMUM";
    }

    @Override
    public void exibirPermissoes() {
        System.out.println("Permissoes: registrar acoes, ver o proprio perfil.");
    }
}
