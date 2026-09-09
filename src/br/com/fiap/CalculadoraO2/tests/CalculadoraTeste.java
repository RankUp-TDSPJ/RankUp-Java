package br.com.fiap.CalculadoraO2.tests;

import br.com.fiap.CalculadoraO2.dao.RegistroAcaoDAO;
import br.com.fiap.CalculadoraO2.dao.UsuarioDAO;
import br.com.fiap.CalculadoraO2.models.*;

import java.util.List;
import java.util.Scanner;

public class CalculadoraTeste {

    private static final String CODIGO_ADMIN = "senha"; /* senha ficticia para apenas as pessoas que possuem a
    senha do admin conseguirem criar uma conta admin */

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RegistroAcaoDAO registroAcaoDAO = new RegistroAcaoDAO();
        Ranking ranking = new Ranking();

        List<AcaoSustentavel> acoes = List.of(
                new AcaoSustentavel("Usar transporte publico", "Reduza emissoes usando onibus",
                        2.5, 10.0, "viagens"),
                new AcaoSustentavel("Reciclar lixo", "Separe o lixo corretamente",
                        1.5, 8.0, "kg"),
                new AcaoSustentavel("Economizar energia", "Desligue aparelhos em standby",
                        3.0, 12.0, "horas")
        );

        int opcaoInicial;

        do {
            System.out.println("\n=== Calculadora de Carbono ===");
            System.out.println("1. Cadastrar novo usuario");
            System.out.println("2. Fazer login");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcaoInicial = leitor.nextInt();
            leitor.nextLine();

            Usuario usuario = null;

            switch (opcaoInicial) {

                case 1:
                    System.out.print("Digite seu nome: ");
                    String nome = leitor.nextLine();
                    System.out.print("Digite seu email: ");
                    String email = leitor.nextLine();
                    System.out.print("Digite sua idade: ");
                    int idade = leitor.nextInt();
                    leitor.nextLine();

                    System.out.print("Se voce tem o codigo de administrador, digite agora (ou aperte Enter para pular): ");
                    String codigoDigitado = leitor.nextLine();

                    if (CODIGO_ADMIN.equals(codigoDigitado)) {
                        usuario = new UsuarioAdmin(nome, email, idade);
                    } else {
                        usuario = new UsuarioComum(nome, email, idade);
                    }

                    int novoId = usuarioDAO.proximoIdUser();
                    usuario.setId(novoId);

                    try {
                        usuarioDAO.cadastrarUser(usuario);
                        System.out.println("Usuario cadastrado como " + usuario.getTipo() + "! ID: " + novoId);
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar no banco: " + e.getMessage());
                        usuario = null;
                    }
                    break;

                case 2:
                    System.out.print("Digite seu email: ");
                    String emailLogin = leitor.nextLine();

                    usuario = usuarioDAO.buscarPorEmailUser(emailLogin);

                    if (usuario == null) {
                        System.out.println("Nenhum usuario encontrado com esse email.");
                    } else {
                        List<RegistroAcao> historico = registroAcaoDAO.listarPorUsuario(usuario.getId(), acoes);
                        usuario.getPontuacao().carregarRegistros(historico);
                        System.out.println("Login realizado! Bem-vindo, " + usuario.getNome());
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

            if (usuario != null) {
                if ("ADMIN".equals(usuario.getTipo())) {
                    menuAdmin(usuario, leitor, usuarioDAO, registroAcaoDAO, ranking, acoes);
                } else {
                    menuComum(usuario, leitor, usuarioDAO, registroAcaoDAO, ranking, acoes);
                }
            }

        } while (opcaoInicial != 0);

        leitor.close();
    }

    private static void menuComum(Usuario usuario, Scanner leitor, UsuarioDAO usuarioDAO, RegistroAcaoDAO registroAcaoDAO, Ranking ranking,
                                  List<AcaoSustentavel> acoes) {

        int opcao;

        do {
            System.out.println("\n=== Menu (COMUM) ===");
            System.out.println("1. Registrar acao sustentavel");
            System.out.println("2. Ver perfil e pontuacao");
            System.out.println("3. Ver minha posicao no ranking");
            System.out.println("4. Ver ranking (top 10)");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    registrarAcao(usuario, leitor, registroAcaoDAO, usuarioDAO, acoes);
                    break;
                case 2:
                    verPerfil(usuario);
                    break;
                case 3:
                    verPosicaoRanking(usuario, usuarioDAO, ranking);
                    break;
                case 4:
                    verRankingTop10(usuarioDAO, ranking);
                    break;
                case 0:
                    System.out.println("Fazendo logout...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

        } while (opcao != 0);
    }

    private static void menuAdmin(Usuario usuario, Scanner leitor, UsuarioDAO usuarioDAO, RegistroAcaoDAO registroAcaoDAO, Ranking ranking,
                                  List<AcaoSustentavel> acoes) {

        int opcao;

        do {
            System.out.println("\n=== Menu (ADMIN) ===");
            System.out.println("1. Registrar acao sustentavel");
            System.out.println("2. Ver perfil e pontuacao");
            System.out.println("3. Ver minha posicao no ranking");
            System.out.println("4. Ver ranking (top 10)");
            System.out.println("5. Listar usuarios do banco");
            System.out.println("6. Deletar usuario do banco");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    registrarAcao(usuario, leitor, registroAcaoDAO, usuarioDAO, acoes);
                    break;
                case 2:
                    verPerfil(usuario);
                    break;
                case 3:
                    verPosicaoRanking(usuario, usuarioDAO, ranking);
                    break;
                case 4:
                    verRankingTop10(usuarioDAO, ranking);
                    break;
                case 5:
                    listarUsuarios(usuarioDAO);
                    break;
                case 6:
                    deletarUsuario(leitor, usuarioDAO, registroAcaoDAO);
                    break;
                case 0:
                    System.out.println("Fazendo logout...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

        } while (opcao != 0);
    }

    private static void registrarAcao(Usuario usuario, Scanner leitor, RegistroAcaoDAO registroAcaoDAO,
                                      UsuarioDAO usuarioDAO, List<AcaoSustentavel> acoes) {

        System.out.println("\n=== Acoes Disponiveis ===");
        for (int acaoo = 0; acaoo < acoes.size(); acaoo++) {
            System.out.println((acaoo + 1) + ". " + acoes.get(acaoo).getNome());
        }

        System.out.print("Escolha uma acao: ");
        int escolha = leitor.nextInt() - 1;
        leitor.nextLine();

        if (escolha < 0 || escolha >= acoes.size()) {
            System.out.println("Opcao invalida!");
            return;
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
        usuario.getPontuacao().adicionarRegistro(registro);
        registroAcaoDAO.cadastrarAcao(usuario.getId(), registro);

        usuarioDAO.updateUser(usuario);

        int nivelImpacto = CalculadoraCarbono.avaliarAcao(acoes.get(escolha), quantidade);
        double co2Evitado = CalculadoraCarbono.calcularImpacto(acoes.get(escolha), quantidade);
        System.out.println("Acao registrada! Nivel de impacto: " + nivelImpacto + "/100");
        System.out.println("CO2 nao emitido nesta acao: " + co2Evitado + " kg");
    }

    private static void verPerfil(Usuario usuario) {
        System.out.println("\n=== Perfil do Usuario ===");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Idade: " + usuario.getIdade());
        System.out.println("Tipo: " + usuario.getTipo());

        System.out.println("\n=== Historico de Acoes ===");
        List<RegistroAcao> registros = usuario.getPontuacao().getRegistros();

        if (registros.isEmpty()) {
            System.out.println("Nenhuma acao registrada ainda.");
        } else {
            for (RegistroAcao r : registros) {
                r.exibirRegistro();
            }
        }

        System.out.println("-------------------------");
        System.out.println("Pontuacao total: " + usuario.getPontuacao().getPontuacaoTotal());
        System.out.println("Nivel: " + usuario.getPontuacao().calcularNivel());
        System.out.println("CO2 total nao emitido: " + usuario.getPontuacao().calcularCo2TotalEvitado() + " kg");
    }

    private static void verPosicaoRanking(Usuario usuario, UsuarioDAO usuarioDAO, Ranking ranking) {
        List<Usuario> todosParaPosicao = usuarioDAO.listarTodosUser();
        int posicao = ranking.posicaoUsuario(usuario, todosParaPosicao);

        if (posicao == -1) {
            System.out.println("Nao foi possivel calcular sua posicao no ranking.");
        } else {
            System.out.println("Sua posicao no ranking: " + posicao + "o lugar (de "
                    + todosParaPosicao.size() + " usuarios)");
        }
    }

    private static void listarUsuarios(UsuarioDAO usuarioDAO) {
        List<Usuario> usuarios = usuarioDAO.listarTodosUser();
        System.out.println("\n=== Usuarios no banco ===");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getId() +
                    " | Nome: " + u.getNome() +
                    " | Tipo: " + u.getTipo() +
                    " | Pontuacao: " + u.getPontuacao().getPontuacaoTotal());
        }
    }

    private static void deletarUsuario(Scanner leitor, UsuarioDAO usuarioDAO, RegistroAcaoDAO registroAcaoDAO) {
        System.out.print("Digite o ID do usuario a deletar: ");
        int idDeletar = leitor.nextInt();
        leitor.nextLine();

        Usuario existente = usuarioDAO.buscarPorIdUser(idDeletar);

        if (existente == null) {
            System.out.println("Nenhum usuario encontrado com esse ID.");
            return;
        }

        registroAcaoDAO.deletarRegistroUser(idDeletar);
        usuarioDAO.deleteUser(idDeletar);
        System.out.println("Usuario deletado com sucesso!");
    }

    private static void verRankingTop10(UsuarioDAO usuarioDAO, Ranking ranking) {
        List<Usuario> todos = usuarioDAO.listarTodosUser();
        List<Usuario> ranqueados = ranking.gerarRanking(todos);

        int limite = Math.min(10, ranqueados.size());

        System.out.println("\n=== Ranking (Top " + limite + ") ===");
        for (int i = 0; i < limite; i++) {
            Usuario u = ranqueados.get(i);
            System.out.println((i + 1) + "o lugar - " + u.getNome() +
                    " (" + u.getPontuacao().getPontuacaoTotal() + " pontos)");
        }
    }
}