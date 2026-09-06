package br.com.fiap.CalculadoraO2.tests;

import br.com.fiap.CalculadoraO2.dao.RegistroAcaoDAO;
import br.com.fiap.CalculadoraO2.dao.UsuarioDAO;
import br.com.fiap.CalculadoraO2.models.*;

import java.util.List;
import java.util.Scanner;

public class CalculadoraTeste {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Usuario usuario = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RegistroAcaoDAO registroAcaoDAO = new RegistroAcaoDAO();
        Ranking ranking = new Ranking();
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
            System.out.println("4. Listar usuarios do banco");
            System.out.println("5. Deletar usuario do banco");
            System.out.println("6. Carregar usuario existente");
            System.out.println("7. Ver ranking");
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

                    System.out.println("Tipo de usuario(1 | 2):");
                    System.out.println("1. Comum");
                    System.out.println("2. Admin");
                    System.out.print("Escolha: ");
                    int tipoEscolhido = leitor.nextInt();
                    leitor.nextLine();

                    if (tipoEscolhido == 1) {
                        usuario = new UsuarioComum(nome, email, idade);
                    } else if (tipoEscolhido == 2) {
                        usuario = new UsuarioAdmin(nome, email, idade);
                    } else {
                        System.out.println("Tipo invalido! Cadastro cancelado.");
                        break;
                    }

                    int novoId = usuarioDAO.proximoIdUser();
                    usuario.setId(novoId);

                    try {
                        usuarioDAO.cadastrarUser(usuario);
                        System.out.println("Usuario cadastrado e salvo no banco com sucesso! ID: " + novoId);
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar no banco: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (usuario == null) {
                        System.out.println("Cadastre ou carregue um usuario primeiro!");
                        break;
                    }

                    System.out.println("\n=== Acoes Disponiveis ===");
                    for (int acaoo = 0; acaoo < acoes.size(); acaoo++) {
                        System.out.println((acaoo + 1) + ". " + acoes.get(acaoo).getNome());
                    }

                    System.out.print("Escolha uma acao: ");
                    int escolha = leitor.nextInt() - 1;
                    leitor.nextLine();

                    if (escolha < 0 || escolha >= acoes.size()) {
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

                    RegistroAcao registro = new RegistroAcao(acoes.get(escolha), quantidade, data);

                    registro.setId_acao(registroAcaoDAO.proximoIdAcao());
                    usuario.getPontuacao().adicionarRegistro(registro); // soma pontos em memoria
                    registroAcaoDAO.cadastrarAcao(usuario.getId(), registro); // salva o historico no banco

                    usuarioDAO.updateUser(usuario); // salva a pontuacao nova no banco

                    int nivelImpacto = CalculadoraCarbono.avaliarAcao(acoes.get(escolha), quantidade);
                    System.out.println("Acao registrada! Nivel de impacto: " + nivelImpacto + "/100");
                    break;

                case 3:
                    if (usuario == null) {
                        System.out.println("Cadastre ou carregue um usuario primeiro!");
                        break;
                    }

                    System.out.println("\n=== Perfil do Usuario ===");
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    System.out.println("Idade: " + usuario.getIdade());
                    System.out.println("Tipo: " + usuario.getTipo());

                    System.out.println("\n=== Historico de Acoes ===");
                    List<RegistroAcao> registros = usuario.getPontuacao().getRegistros();
                    int total = registros.size();

                    if (total == 0) {
                        System.out.println("Nenhuma acao registrada ainda.");
                    } else {
                        for (RegistroAcao r : registros) {
                            if (r != null) {
                                r.exibirRegistro();
                            }
                        }
                        System.out.println("-------------------------");
                        System.out.println("Pontuacao total: " + usuario.getPontuacao().getPontuacaoTotal());
                        System.out.println("Nivel: " + usuario.getPontuacao().calcularNivel());
                    }
                    break;

                case 4:
                    try {
                        List<Usuario> usuarios = usuarioDAO.listarTodosUser();
                        System.out.println("\n=== Usuarios no banco ===");
                        for (Usuario u : usuarios) {
                            System.out.println("ID: " + u.getId() +
                                    " | Nome: " + u.getNome() +
                                    " | Tipo: " + u.getTipo() +
                                    " | Pontuacao: " + u.getPontuacao().getPontuacaoTotal());
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao listar: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Digite o ID do usuario a deletar: ");
                    int idDeletar = leitor.nextInt();
                    leitor.nextLine();
                    try {
                        usuarioDAO.deleteUser(idDeletar);
                        System.out.println("Usuario deletado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao deletar: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Digite o ID do usuario que deseja carregar: ");
                    int idCarregar = leitor.nextInt();
                    leitor.nextLine();

                    usuario = usuarioDAO.buscarPorIdUser(idCarregar);

                    if (usuario == null) {
                        System.out.println("Nenhum usuario encontrado com esse ID.");
                    } else {
                        System.out.println("Usuario carregado: " + usuario.getNome());
                    }
                    break;

                case 7:
                    try {
                        List<Usuario> todos = usuarioDAO.listarTodosUser();
                        List<Usuario> ranqueados = ranking.gerarRanking(todos);

                        System.out.println("\n=== Ranking ===");
                        for (int i = 0; i < ranqueados.size(); i++) {
                            Usuario u = ranqueados.get(i);
                            System.out.println((i + 1) + "o lugar - " + u.getNome() +
                                    " (" + u.getPontuacao().getPontuacaoTotal() + " pontos)");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao gerar ranking: " + e.getMessage());
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