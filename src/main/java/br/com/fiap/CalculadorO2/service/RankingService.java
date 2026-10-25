package br.com.fiap.CalculadorO2.service;

import br.com.fiap.CalculadorO2.dao.UsuarioDAO;
import br.com.fiap.CalculadorO2.exception.ApiException;
import br.com.fiap.CalculadorO2.models.Ranking;
import br.com.fiap.CalculadorO2.models.Usuario;

import java.util.List;

public class RankingService {

    public record Posicao(int posicao, int totalUsuarios, double pontuacaoTotal) {}

    private final UsuarioDAO usuarioDAO;
    private final Ranking ranking = new Ranking();

    public RankingService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> top(int limite) {
        if (limite <= 0) {
            throw new ApiException(400, "Limite deve ser maior que 0");
        }
        List<Usuario> ordenados = ranking.gerarRanking(usuarioDAO.listarTodosUser());
        return ordenados.subList(0, Math.min(limite, ordenados.size()));
    }

    public Posicao posicao(int idUsuario) {
        Usuario usuario = usuarioDAO.buscarPorIdUser(idUsuario);
        if (usuario == null) {
            throw new ApiException(404, "Usuário não encontrado");
        }
        List<Usuario> todos = usuarioDAO.listarTodosUser();
        int posicao = ranking.posicaoUsuario(usuario, todos);
        return new Posicao(posicao, todos.size(), usuario.getPontuacao().getPontuacaoTotal());
    }
}
