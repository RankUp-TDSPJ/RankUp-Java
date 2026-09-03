package br.com.fiap.CalculadoraO2.models;

import br.com.fiap.CalculadoraO2.interfaces.Exibivel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PontuacaoUsuario implements Exibivel {

    private double pontuacaoTotal;
    private List<RegistroAcao> registros;

    public PontuacaoUsuario() {
        this.pontuacaoTotal = 0;
        this.registros = new ArrayList<>();
    }

    public int contarTiposDistintos() {
        Set<String> tiposDistintos = new HashSet<>();

        for (RegistroAcao r : registros) {
            tiposDistintos.add(r.getAcao().getNome());
        }

        return tiposDistintos.size();
    }

    public String calcularNivel() {
        int diversidade = contarTiposDistintos();

        if (pontuacaoTotal >= 1500 && diversidade >= 3) {
            return "OURO";
        } else if (pontuacaoTotal >=600 && diversidade >= 2) {
            return "PRATA";
        } else if (pontuacaoTotal >= 150) {
            return "BRONZE";
        } else {
            return "INICIANTE";
        }
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
