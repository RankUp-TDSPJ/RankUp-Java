package br.com.fiap.CalculadoraO2.tests;

import br.com.fiap.CalculadoraO2.models.*;
import java.util.Scanner;

public class CalculadoraTeste {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Usuario usuario = null;
        int opcao = 0;

        AcaoSustentavel[] acoes = {
                new AcaoSustentavel("Usar transporte publico", "Reduza emissoes usando onibus",
                        2.5, 10.0, "viagens"),
                new AcaoSustentavel("Reciclar lixo", "Separe o lixo corretamente",
                        1.5, 8.0, "kg"),
                new AcaoSustentavel("Economizar energia", "Desligue aparelhos em standby",
                        3.0, 12.0, "horas")
        };

        do {
            System.out.println("\n=== Calculadora de Carbono ===");
            System.out.println("1. Cadastrar usuario");
            System.out.println("2. Registrar acao sustentavel");
            System.out.println("3. Ver perfil e pontuacao");
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
                    if (usuario == null) {
                        System.out.println("Cadastre um usuario primeiro!");
                        break;
                    }


                    System.out.println("\n=== Acoes Disponiveis ===");
                    for (int acaoo = 0; acaoo < acoes.length; acaoo++) {
                        System.out.println((acaoo + 1) + ". " + acoes[acaoo].getNome());
                    }

                    System.out.print("Escolha uma acao: ");
                    int escolha = leitor.nextInt() - 1;
                    leitor.nextLine();

                    if (escolha < 0 || escolha >= acoes.length) {
                        System.out.println("Opcao invalida!");
                        break;
                    }

                    double quantidade = 0;

                    if (escolha == 0) {
                        System.out.print("Quantas viagens de transporte publico voce fez? ");
                        quantidade = leitor.nextDouble();
                        leitor.nextLine();
                    } else if (escolha == 1) {
                        System.out.print("Quantos kg de lixo voce reciclou? ");
                        quantidade = leitor.nextDouble();
                        leitor.nextLine();
                    } else if (escolha == 2) {
                        System.out.print("Quantas horas ficou sem usar energia? ");
                        quantidade = leitor.nextDouble();
                        leitor.nextLine();
                    }

                    System.out.print("Digite a data (ex: 13/05/2026): ");
                    String data = leitor.nextLine();

                    RegistraAcao registro = new RegistraAcao(acoes[escolha], quantidade, data);
                    usuario.getPontuacao().adicionarRegistro(registro);

                    int nivelImpacto = CalculadoraCarbono.avaliarAcao(acoes[escolha], quantidade);
                    System.out.println("Acao registrada! Nivel de impacto: " + nivelImpacto + "/100");
                    break;

                case 3:
                    if (usuario == null) {
                        System.out.println("Cadastre um usuario primeiro!");
                        break;
                    }

                    System.out.println("\n=== Perfil do Usuario ===");
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    System.out.println("Idade: " + usuario.getIdade());

                    System.out.println("\n=== Historico de Acoes ===");
                    RegistraAcao[] registros = usuario.getPontuacao().getRegistros();
                    int total = usuario.getPontuacao().getTotalRegistros();

                    if (total == 0) {
                        System.out.println("Nenhuma acao registrada ainda.");
                    } else {
                        for (RegistraAcao r : registros) {
                            if (r != null) {
                                r.exibirRegistro();
                            }
                        }
                        System.out.println("-------------------------");
                        System.out.println("Pontuacao total: " + usuario.getPontuacao().getPontuacaoTotal());
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