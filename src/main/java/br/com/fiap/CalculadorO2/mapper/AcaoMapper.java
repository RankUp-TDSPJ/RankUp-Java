package br.com.fiap.CalculadorO2.mapper;

import br.com.fiap.CalculadorO2.models.AcaoSustentavel;

public class AcaoMapper {

    public static AcaoResponse toResponse(AcaoSustentavel a) {
        return new AcaoResponse(a.getNome(), a.getDescricao(), a.getUnidade(),
                a.getPontosPorAcao(), a.getFatorCO2());
    }
}
