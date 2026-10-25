package br.com.fiap.CalculadorO2.mapper;

import br.com.fiap.CalculadorO2.models.PontuacaoUsuario;
import br.com.fiap.CalculadorO2.models.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getIdade(), u.getTipo());
    }

    public static PerfilResponse toPerfil(Usuario u) {
        PontuacaoUsuario p = u.getPontuacao();
        return new PerfilResponse(u.getId(), u.getNome(), u.getEmail(), u.getIdade(), u.getTipo(),
                p.getPontuacaoTotal(), p.calcularNivel(), p.calcularCo2TotalEvitado());
    }

    public static UsuarioResumoResponse toResumo(Usuario u) {
        return new UsuarioResumoResponse(u.getId(), u.getNome(), u.getEmail(), u.getIdade(), u.getTipo(),
                u.getPontuacao().getPontuacaoTotal());
    }

    public static RankingItemResponse toRankingItem(int posicao, Usuario u) {
        return new RankingItemResponse(posicao, u.getId(), u.getNome(), u.getPontuacao().getPontuacaoTotal());
    }
}
