package br.com.fiap.CalculadoraO2.tests;

import br.com.fiap.CalculadoraO2.dao.UsuarioDAO;
import br.com.fiap.CalculadoraO2.models.*;

import java.util.List;
import java.util.Scanner;

public class CalculadoraTeste {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Usuario usuario = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int opcao = 0;

        List<AcaoSustentavel> acoes = List.of(
                new AcaoSustentavel("Usar transporte publico", "Reduza emissoes usando onibus",
                        2.5, 10.0, "viagens"),
                new AcaoSustentavel("Reciclar lixo", "Separe o lixo corretamente",
                        1.5, 8.0, "kg"),
                new AcaoSustentavel("Economizar energia", "Desligue aparelhos em standby",
                        3.0, 12.0, "horas")
        );

        do {
            System.out.println("\n=== Calculadora de Carbono ===");
            System.out.println("1. Cadastrar usuario");
            System.out.println("2. Registrar acao sustentavel");
            System.out.println("3. Ver perfil e pontuacao");
            System.out.println("4. Salvar usuario no banco");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Digite seu nome: ");
                    String nome = leitor.nextLine();
                    System.out.print("Digite seu email: ");
                    String email = leitor.nextLine();
                    System.out.print("Digite sua idade: ");
                    int idade = leitor.nextInt();
                    leitor.nextLine();
                    usuario = new Usuario(nome, email, idade);
                    System.out.println("Usuario cadastrado com sucesso!");
                    break;

                case 2:
                    // ... (sem alteração, mantém como está)
                    break;

                case 3:
                    // ... (sem alteração, mantém como está)
                    break;

                case 4:
                    if (usuario == null) {
                        System.out.println("Cadastre um usuario primeiro (opcao 1)!");
                        break;
                    }
                    try {
                        usuarioDAO.cadastrar(usuario);
                        System.out.println("Usuario salvo no banco com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar no banco: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

        } while (opcao != 0);

        leitor.close();
    }
}