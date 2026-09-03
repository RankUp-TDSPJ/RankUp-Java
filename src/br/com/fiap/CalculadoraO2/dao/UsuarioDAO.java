package br.com.fiap.CalculadoraO2.dao;

import br.com.fiap.CalculadoraO2.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Usuario> listarTodos() {
        conexao = ConnectionFactory.obterconexao();
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from tbl_usuario";
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario(rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                user.getPontuacao().setPontuacaoTotal(rs.getDouble(5));
                usuarios.add(user);
            }
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    public void update(Usuario usuario) {
        conexao = ConnectionFactory.obterconexao();
        PreparedStatement ps = null;

        try {
            String sql = "update tbl_usuario set NOME_USUARIO = ?, EMAIL_USUARIO = ?, " +
                    "IDADE_USUARIO = ?, PONTUACAOTOTAL_USUARIO = ? where ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getIdade());
            ps.setDouble(4, usuario.getPontuacao().getPontuacaoTotal());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {
        conexao = ConnectionFactory.obterconexao();
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(
                    "DELETE FROM TBL_USUARIO WHERE ID_USUARIO = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }










}
