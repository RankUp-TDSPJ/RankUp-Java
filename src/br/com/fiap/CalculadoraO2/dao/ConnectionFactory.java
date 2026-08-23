package br.com.fiap.CalculadoraO2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection obterconexao() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                    "rm571211",
                    "231207");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexao;
    }

}
