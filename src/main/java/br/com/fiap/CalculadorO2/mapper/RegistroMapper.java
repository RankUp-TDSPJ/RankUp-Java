package br.com.fiap.CalculadorO2.mapper;

import br.com.fiap.CalculadorO2.models.RegistroAcao;
import br.com.fiap.CalculadorO2.models.Usuario;

public class RegistroMapper {

        // usuario já vem com o registro novo somado, então pontuação e nível saem atualizados
        public static RegistroCriadoResponse toCriado(RegistroAcao r, Usuario u) {
            return new RegistroCriadoResponse(r.getId_acao(), r.getAcao().getNome(), r.getQuantidade(), r.getData(),
                    r.calcularPontos(), r.calcularImpactoCO2(),
                    u.getPontuacao().getPontuacaoTotal(), u.getPontuacao().calcularNivel());
        }

        public static HistoricoResponse toHistorico(RegistroAcao r) {
            return new HistoricoResponse(r.getId_acao(), r.getAcao().getNome(), r.getAcao().getUnidade(),
                    r.getQuantidade(), r.getData(), r.calcularPontos(), r.calcularImpactoCO2());
        }
}
