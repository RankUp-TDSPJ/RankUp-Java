package br.com.fiap.CalculadoraO2.dao;

import br.com.fiap.CalculadoraO2.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection conexao;

    public void cadastrar(Usuario usuario) {
        conexao = ConnectionFactory.obterconexao();
        PreparedStatement ps = null;

        try {
                String sql = "insert into tbl_usuario (ID_USUARIO, NOME_USUARIO, EMAIL_USUARIO, " +
                        "IDADE_USUARIO, PONTUACAOTOTAL_USUARIO)" +
                        "values(?, ?, ?, ?, ?)";

            ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, usuario.getIdade());
            ps.setDouble(5, usuario.getPontuacao().getPontuacaoTotal());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
