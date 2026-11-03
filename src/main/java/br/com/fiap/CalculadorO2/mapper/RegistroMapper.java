package br.com.fiap.CalculadorO2.mapper;

import br.com.fiap.CalculadorO2.dto.HistoricoResponse;
import br.com.fiap.CalculadorO2.dto.RegistroCriadoResponse;
import br.com.fiap.CalculadorO2.models.RegistroAcao;
import br.com.fiap.CalculadorO2.models.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegistroMapper {

        private static final DateTimeFormatter FORMATO_ANTIGO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // registros antigos foram gravados como dd/MM/yyyy; a API sempre devolve yyyy-MM-dd
        private static String normalizarData(String data) {
            if (data == null) {
                return null;
            }
            try {
                return LocalDate.parse(data, FORMATO_ANTIGO).toString();
            } catch (DateTimeParseException e) {
                return data;
            }
        }

        public static RegistroCriadoResponse toCriado(RegistroAcao r, Usuario u) {
            return new RegistroCriadoResponse(r.getId_acao(), r.getAcao().getNome(), r.getQuantidade(), normalizarData(r.getData()),
                    r.calcularPontos(), r.calcularImpactoCO2(),
                    u.getPontuacao().getPontuacaoTotal(), u.getPontuacao().calcularNivel());
        }

        public static HistoricoResponse toHistorico(RegistroAcao r) {
            return new HistoricoResponse(r.getId_acao(), r.getAcao().getNome(), r.getAcao().getUnidade(),
                    r.getQuantidade(), normalizarData(r.getData()), r.calcularPontos(), r.calcularImpactoCO2());
        }
}
