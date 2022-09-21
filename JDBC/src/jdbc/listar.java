package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class listar {

    public static void main() {

        final String obrigatorio
                = "?autoReconnect=true&useSSL=false&&serverTimezone=UTC";

        final String url
                = "jdbc:mysql://localhost/trabjdbc" + obrigatorio;

        final String sql = "SELECT * FROM produtos";

        try (
                 Connection conexao = DriverManager.getConnection(url, "root", "");  Statement statement = conexao.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();

            int numeroColunas = metaData.getColumnCount();

            for (int i = 1; i <= numeroColunas; i++) {
                System.out.printf("%-8s\t",
                        metaData.getColumnName(i));
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= numeroColunas; i++) {
                    System.out.printf("%-8s\t",
                            resultSet.getObject(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
