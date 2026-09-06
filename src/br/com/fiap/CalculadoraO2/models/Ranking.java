package br.com.fiap.CalculadoraO2.models;

import java.util.Comparator;
import java.util.List;

public class Ranking {

    public List<Usuario> gerarRanking(List<Usuario> usuarios) {
        usuarios.sort(Comparator.comparingDouble((Usuario u) -> u.getPontuacao().getPontuacaoTotal()).reversed());
        return usuarios;

    }

    public int posicaoUsuario(Usuario userAlvo, List<Usuario> usuarios) {
        List<Usuario> ranking = gerarRanking(usuarios);

        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i).getId() == userAlvo.getId()) {
                return i + 1;
            }
        }

        return -1;
    }
}
