package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class sumar {

    public static void main() {

        int id;
        int n = 0;
        int qtd = 0;
        final String obrigatorio = "?autoReconnect=true&useSSL=false&&serverTimezone=UTC";

        final String url = "jdbc:mysql://localhost/trabjdbc" + obrigatorio;
        final String sql = "Select * FROM produtos";

        while (true) {
            try {

                System.out.println("Id del producto: ");
                id = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Informe um numero");
            }
        }

        final String sqlw = "SELECT DISTINCT `Quantidade` FROM `produtos` WHERE `Id`= " + id + "";

        try (
                 Connection conexao = DriverManager.getConnection(url, "root", "");  Statement statement = conexao.createStatement();  ResultSet resultSet = statement.executeQuery(sqlw)) {

            ResultSetMetaData metaData = resultSet.getMetaData();

            int numeroColunas = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= numeroColunas; i++) {

                    n = Integer.parseInt(resultSet.getObject(i).toString());

                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            try {

                System.out.println("Cantidad que desea agregar: ");
                qtd = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Informe um numero");
            }
        }

        qtd = qtd + n;

        try {
            Connection conexao = DriverManager.getConnection(url, "root", "");

            PreparedStatement insert = (PreparedStatement) conexao
                    .prepareStatement("UPDATE `produtos` SET `Quantidade` = '" + qtd + "' WHERE `produtos`.`Id` = " + id + ";");

            int retorno = insert.executeUpdate();

            if (retorno > 0) {
                System.out.println("Sucesso");
            } else {
                System.out.println("Sem sucesso");
            }

            Statement select = conexao.createStatement();
            ResultSet resultSet = select.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numeroColunas = metaData.getColumnCount();

            for (int i = 1; i <= numeroColunas; i++) {
                System.out.printf("%-8s\t",
                        metaData.getColumnName(i));
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= numeroColunas; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println();

            }

            conexao.close();
            insert.close();
            select.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
