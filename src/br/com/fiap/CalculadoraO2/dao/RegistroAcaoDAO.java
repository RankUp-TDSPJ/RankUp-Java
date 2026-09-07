package br.com.fiap.CalculadoraO2.dao;

import br.com.fiap.CalculadoraO2.models.AcaoSustentavel;
import br.com.fiap.CalculadoraO2.models.RegistroAcao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroAcaoDAO {

    private Connection conexao;

    public int proximoIdAcao() {
        conexao = ConnectionFactory.obterconexao();
        int proximo = 1;
        try {
            String sql = "select max(ID_REGISTRO) as maior from tbl_registro_acao";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proximo = rs.getInt("maior") + 1;
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proximo;
    }

    public void cadastrarAcao(int idUsuario, RegistroAcao registro) {
        conexao = ConnectionFactory.obterconexao();
        PreparedStatement ps = null;

        try {
            String sql = "insert into tbl_registro_acao (ID_REGISTRO, ID_USUARIO, NOME_ACAO, " +
                    "QUANTIDADE, DATA_REGISTRO, PONTOS_GANHOS) values (?, ?, ?, ?, ?, ?)";

            ps = conexao.prepareStatement(sql);
            ps.setInt(1, registro.getId_acao());
            ps.setInt(2, idUsuario);
            ps.setString(3, registro.getAcao().getNome());
            ps.setDouble(4, registro.getQuantidade());
            ps.setString(5, registro.getData());
            ps.setDouble(6, registro.calcularPontos());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarRegistroUser(int idUsuario) {
        conexao = ConnectionFactory.obterconexao();
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement("DELETE FROM TBL_REGISTRO_ACAO WHERE ID_USUARIO = ?");
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegistroAcao> listarPorUsuario(int idUsuario, List<AcaoSustentavel> acoesDisponiveis) {
        conexao = ConnectionFactory.obterconexao();
        List<RegistroAcao> registros = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from tbl_registro_acao where ID_USUARIO = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nomeAcao = rs.getString("NOME_ACAO");
                AcaoSustentavel acaoEncontrada = null;

                for (AcaoSustentavel a : acoesDisponiveis) {
                    if (a.getNome().equals(nomeAcao)) {
                        acaoEncontrada = a;
                        break;
                    }
                }

                if (acaoEncontrada != null) {
                    double quantidade = rs.getDouble("QUANTIDADE");
                    String data = rs.getString("DATA_REGISTRO");
                    RegistroAcao registro = new RegistroAcao(acaoEncontrada, quantidade, data);
                    registro.setId_acao(rs.getInt("ID_REGISTRO"));
                    registros.add(registro);
                }
            }

            rs.close();
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return registros;
    }

}
