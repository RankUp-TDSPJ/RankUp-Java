package br.com.fiap.CalculadorO2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection obterconexao() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    System.getenv("SPRING_DATASOURCE_URL"),
                    System.getenv("SPRING_DATASOURCE_USERNAME"),
                    System.getenv("SPRING_DATASOURCE_PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexao;
    }

}
