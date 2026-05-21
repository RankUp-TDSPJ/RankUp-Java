package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

public class PontuacaoUsuario implements Exibivel {

    private double pontuacaoTotal;
    private RegistraAcao[] registros;
    private int totalRegistros;

    public PontuacaoUsuario() {
        this.pontuacaoTotal = 0;
        this.registros = new RegistraAcao[10];
        this.totalRegistros = 0;
    }

    public void adicionarRegistro(RegistraAcao registro) {
        if (totalRegistros < registros.length) {
            registros[totalRegistros] = registro;
            totalRegistros++;
            this.pontuacaoTotal += registro.calcularPontos();
        } else {
            System.out.println("Limite de acoes atingido!");
        }
    }

    public void exibirPontuacao() {
        System.out.println("\n=== Historico de Acoes ===");
        if (totalRegistros == 0) {
            System.out.println("Nenhuma acao registrada ainda.");
            return;
        }
        for (int i = 0; i < totalRegistros; i++) {
            registros[i].exibirRegistro();
        }
        System.out.println("-------------------------");
        System.out.println("Pontuacao total: " + pontuacaoTotal);
    }

    @Override
    public void exibir() {
        exibirPontuacao();
    }



    public RegistraAcao[] getRegistros() {
        return registros;
    }
    public double getPontuacaoTotal() {
        return pontuacaoTotal;
    }
    public int getTotalRegistros() {
        return totalRegistros;
    }

}
