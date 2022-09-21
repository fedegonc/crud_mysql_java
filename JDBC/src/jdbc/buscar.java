package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class buscar {

    public static void main() {

        final String obrigatorio
                = "?autoReconnect=true&useSSL=false&&serverTimezone=UTC";

        final String url
                = "jdbc:mysql://localhost/trabjdbc" + obrigatorio;

        System.out.println("Nombre: ");
        String nome = new Scanner(System.in).next();
        boolean p = false;

        final String sql = "SELECT * FROM `produtos` WHERE `Nome` LIKE '" + nome + "'";
        final String sqlw = "SELECT `Nome` FROM `produtos`";

        try (
                 Connection conexao = DriverManager.getConnection(url, "root", "");  Statement statement = conexao.createStatement();  ResultSet resultSet = statement.executeQuery(sqlw)) {

            ResultSetMetaData metaData = resultSet.getMetaData();

            int numeroColunas = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= numeroColunas; i++) {
                    if (resultSet.getObject(i).equals(nome)) {
                        p = true;
                    }
                }
            }
            if (p == true) {
                        System.out.println("El producto se encuentra registrado");
                    } else {
                        System.out.println("El producto no se encuentra registrado");
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        if(p == true){
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
}
