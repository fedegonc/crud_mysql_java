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

public class eliminar {

    public static void main() {

        int id;
        final String obrigatorio = "?autoReconnect=true&useSSL=false&&serverTimezone=UTC";

//define a url de banco de dados // teste é o nome do banco
        final String url = "jdbc:mysql://localhost/trabjdbc" + obrigatorio;
        final String sql = "Select * FROM produtos";

        while (true) {
            try {

                System.out.println("Id del producto a eliminar: ");
                id = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Informe um numero");
            }
        }

        try {
            Connection conexao = DriverManager.getConnection(url, "root", "");

            PreparedStatement delete = (PreparedStatement) conexao
                    .prepareStatement("DELETE FROM produtos WHERE `produtos`.`Id` = " + id + "");

            int retorno = delete.executeUpdate();

            if (retorno > 0) {
                System.out.println("Sucesso");
            } else {
                System.out.println("Sem sucesso");
            }

            Statement select = conexao.createStatement();
            ResultSet resultSet = select.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            int numeroColunas = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= numeroColunas; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println();

            }

            conexao.close();
            delete.close();
            select.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
