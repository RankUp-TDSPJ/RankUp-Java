package br.com.fiap.CalculadorO2.service;

import br.com.fiap.CalculadorO2.models.AcaoSustentavel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoService {

    private final List<AcaoSustentavel> acoes = List.of(
            new AcaoSustentavel("Usar transporte publico", "Reduza emissoes usando onibus", 2.5, 10.0, "viagens"),
            new AcaoSustentavel("Reciclar lixo", "Separe o lixo corretamente", 1.5, 8.0, "kg"),
            new AcaoSustentavel("Economizar energia", "Desligue aparelhos em standby", 3.0, 12.0, "horas")
    );

    public List<AcaoSustentavel> listar() {
        return acoes;
    }

    public AcaoSustentavel buscarPorNome(String nome) {
        for (AcaoSustentavel a : acoes) {
            if (a.getNome().equals(nome)) {
                return a;
            }
        }
        return null;
    }
}
