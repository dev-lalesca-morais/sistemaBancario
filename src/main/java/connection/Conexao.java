package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection fazerConexao() {
        try {
            String url = "jdbc:postgresql://localhost:5432/escola";
            Connection connection = DriverManager.getConnection(url, "postgres", "1234");

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }
    }

}
