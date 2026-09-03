package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

import java.util.ArrayList;
import java.util.List;

public class PontuacaoUsuario implements Exibivel {

    private double pontuacaoTotal;
    private List<RegistroAcao> registros;

    public PontuacaoUsuario() {
        this.pontuacaoTotal = 0;
        this.registros = new ArrayList<>();
    }

    public void adicionarRegistro(RegistroAcao registro) {
        registros.add(registro);
        this.pontuacaoTotal += registro.calcularPontos();
        System.out.println("\n=== Registro Adicionado com Sucesso ===");
    }

    public void exibirPontuacao() {
        System.out.println("\n=== Historico de Acoes ===");
        for (RegistroAcao r : registros) { r.exibirRegistro(); }
    }

    @Override
    public void exibir() {
        exibirPontuacao();
    }

    public List<RegistroAcao> getRegistros() {
        return registros;
    }
    public double getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(double pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }
}
