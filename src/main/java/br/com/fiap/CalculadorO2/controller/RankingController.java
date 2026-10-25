package br.com.fiap.CalculadorO2.controller;

import br.com.fiap.CalculadorO2.mapper.UsuarioMapper;
import br.com.fiap.CalculadorO2.models.Usuario;
import br.com.fiap.CalculadorO2.service.RankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingBO) {
        this.rankingService = rankingBO;
    }

    @GetMapping("/ranking")
    public List<RankingItemResponse> ranking(@RequestParam(name = "limite", defaultValue = "10") int limite) {
        List<Usuario> top = rankingService.top(limite);

        List<RankingItemResponse> resposta = new ArrayList<>();
        for (int i = 0; i < top.size(); i++) {
            resposta.add(UsuarioMapper.toRankingItem(i + 1, top.get(i)));   // posição = índice + 1
        }
        return resposta;
    }

    // o record Posicao da RankingBO já tem os campos do contrato, então serve direto
    @GetMapping("/usuarios/{id}/posicao")
    public RankingService.Posicao posicao(@PathVariable("id") int id) {
        return rankingService.posicao(id);
    }
}
