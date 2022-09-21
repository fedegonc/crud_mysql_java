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

public class cadastrar {

    public static void main() {

        int precio;
        int qtd;

        Scanner input = new Scanner(System.in);

        final String obrigatorio = "?autoReconnect=true&useSSL=false&&serverTimezone=UTC";

        final String url = "jdbc:mysql://localhost/trabjdbc" + obrigatorio;
        final String sql = "Select * FROM produtos";

        try {
            Connection conexao = DriverManager.getConnection(url, "root", "");

            PreparedStatement insert = (PreparedStatement) conexao
                    .prepareStatement("INSERT INTO produtos (Nome , Quantidade, Preco) VALUES (?, ?, ?)");

            System.out.println("Ingrese los datos del nuevo producto: ");

            System.out.println("Nombre: ");
            String nome = new Scanner(System.in).next();

            while (true) {
                try {

                    System.out.println("Cantidad: ");
                    qtd = new Scanner(System.in).nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Informe um numero");
                }
            }

            while (true) {
                try {

                    System.out.println("Precio: ");
                    precio = new Scanner(System.in).nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Informe um numero");
                }
            }

            insert.setString(1, nome);
            insert.setInt(2, qtd);
            insert.setInt(3, precio);

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
