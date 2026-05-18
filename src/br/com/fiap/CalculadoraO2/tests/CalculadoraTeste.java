package br.com.fiap.CalculadoraO2.tests;

import br.com.fiap.CalculadoraO2.models.AcaoSustentavel;

import java.util.Scanner;

public class CalculadoraTeste {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        AcaoSustentavel acao = new AcaoSustentavel(
                "Usar transporte publico", "Reduza emissoes usando onibus ou metro",
                2.5, 10.0);

        acao.exibirAcao();

    }
}
